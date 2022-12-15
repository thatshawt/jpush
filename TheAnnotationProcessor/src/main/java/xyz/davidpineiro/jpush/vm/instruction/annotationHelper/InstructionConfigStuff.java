package xyz.davidpineiro.jpush.vm.instruction.annotationHelper;

import com.google.auto.service.AutoService;
import org.apache.commons.io.IOUtils;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

@SupportedAnnotationTypes(
        "xyz.davidpineiro.jpush.vm.instruction.annotationHelper.GenerateStackIntructions")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class InstructionConfigStuff extends AbstractProcessor {

    //debugging...
    public static void main(String[] args) throws IOException, URISyntaxException {
        URI uri = InstructionConfigStuff.class.getResource(INSTRUCTION_RESOURCE_PATH).toURI();
        Path myPath;
        if (uri.getScheme().equals("jar")) {
            FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
            myPath = fileSystem.getPath("/resources");
        } else {
            myPath = Paths.get(uri);
        }
        Stream<Path> walk = Files.walk(myPath, 1);
        for (Iterator<Path> it = walk.iterator(); it.hasNext();){
            File file = it.next().toFile();
            System.out.println(file.isDirectory());
        }
    }

    InstructionTemplate[] stackInstructionTemplates = null;
//    Configuration freeMarkerConfig;
    Messager messager;

    enum TemplateVariable{
        STACK_NAME("stack_name"),
        STACK_JAVA_TYPE("stack_java_type"),
        STACK_VM_NAME("stack_vm_name"),
        STACK_INSTRUCTION_PATH("stack_instruction_path"),
        ;

        public final String a;
        public String toString(){
            return this.a;
        }

        TemplateVariable(String sus) {
            this.a = sus;
        }
    }

    public static final String INSTRUCTION_RESOURCE_PATH = "/instructions";

    @Override
    public void init(ProcessingEnvironment env) {
        try {
            URI uri = InstructionConfigStuff.class.getResource("/instructions").toURI();
            Path myPath;
            System.out.println("the thing: " + uri);
            if (uri.getScheme().equals("jar")) {
                FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
                System.out.println("the files:" + fileSystem);
                myPath = fileSystem.getPath("/instructions");
            } else {
                System.out.println("sussy baka");
                myPath = Paths.get(uri);
            }
            System.out.println("after the fact...");
            Stream<Path> walk = Files.walk(myPath, 1);
            List<InstructionTemplate> templatesTemp = new ArrayList<>();
            for (Iterator<Path> it = walk.iterator(); it.hasNext(); ) {
                Path path = it.next();
                System.out.println("considering..." + path);
                if(path.toString().endsWith("instruction") || !path.toString().endsWith(".ftl"))continue;
                System.out.println("going with " + path);
                InputStream in = Files.newInputStream(path);
                final String templateData = IOUtils.toString(in, Charset.defaultCharset());
                final String templateName = path.getFileName().toString();
                templatesTemp.add(new InstructionTemplate(templateName, templateData));
            }
            stackInstructionTemplates = templatesTemp.toArray(new InstructionTemplate[]{});

            System.out.println("after init: " + Arrays.toString(stackInstructionTemplates));
        } catch (URISyntaxException | IOException e) {
            System.out.println("caught error and printed it");
            e.printStackTrace();
        }

//        instructionTemplateDir = uri.;

        messager = env.getMessager();
        super.init(env);
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//        System.out.println("started process at least");
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements
                    = roundEnv.getElementsAnnotatedWith(annotation);

            for(Element element : annotatedElements){
                //needs to be a class or interface
                if(!(element instanceof TypeElement))continue;

                List<? extends Element> enclosedElements =
                    element.getEnclosedElements();

                Map<TemplateVariable, String> valueMapping = new HashMap<>();

                for(Element enclosed : enclosedElements){
                    if(!(enclosed instanceof VariableElement))continue;
                    VariableElement var = (VariableElement) enclosed;

                    String varName = var.getSimpleName().toString();
                    String value = (String) var.getConstantValue();

                    Arrays.stream(TemplateVariable.values()).forEach(varThing -> {
                        if(varThing.a.equals(varName)) {
//                            valueMapping.put(varName, value);
                            valueMapping.put(varThing, value);
                        }
                    });
                }
                if(!valueMapping.containsKey(TemplateVariable.STACK_INSTRUCTION_PATH)){
                    TypeElement classElement = ((TypeElement) element);
                    valueMapping.put(
                            TemplateVariable.STACK_INSTRUCTION_PATH,
                            classElement.getQualifiedName().toString()
                             .replace("." + element.getSimpleName().toString(), "")
                    );
                }
                if(valueMapping.size() != TemplateVariable.values().length){
                    messager.printMessage(Diagnostic.Kind.ERROR,
                            "values not correct. " +
                                    Arrays.toString(valueMapping.keySet().toArray())
                            );
                    return false;
                }

                return createStackInstructions((TypeElement) element, valueMapping);
            }
        }

        return true;
    }

    private boolean createStackInstructions(TypeElement classElement,
                                            Map<TemplateVariable, String> valueMapping) {
        System.out.println("create stack instructions for " + classElement.getSimpleName());
//        File[] templates = instructionTemplateDir.listFiles();
        System.out.println(Arrays.toString(stackInstructionTemplates));
        for(InstructionTemplate instructionTemplate : stackInstructionTemplates){

            try {
//                Template template = freeMarkerConfig.getTemplate(instructionTemplate.templateName);
//                String template = instructionTemplate.templateString;

                String instructionName = instructionTemplate.templateName
                        .replace(".ftl", "");//take out end thing if its there
                instructionName = valueMapping.get(TemplateVariable.STACK_NAME)
                        + instructionName.substring("Stack".length());//remove leading "Stack"

                //${stack_instruction_path}.${stack_package_name}.stack
                String className =
                        valueMapping.get(TemplateVariable.STACK_INSTRUCTION_PATH)
                        + ".stack." + instructionName;

                JavaFileObject javaFileObject =
                        processingEnv.getFiler().createSourceFile(className);

                System.out.println("adding " + className);

                Writer writer = javaFileObject.openWriter();

                instructionTemplate.process(valueMapping, writer);

                writer.close();
                System.out.println("wrote it");
            }catch(Exception exception){
                messager.printMessage(Diagnostic.Kind.ERROR, exception.getMessage());
            }
        }
        return true;
    }


}
