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
        URI uri = InstructionConfigStuff.class.getResource(INSTRUCTIONS_OPERATORS_PATH).toURI();
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

//    Configuration freeMarkerConfig;
    Messager messager;

    InstructionTemplate[] stackInstructionTemplates = null;
    InstructionTemplate[] operatorTemplates = null;
    InstructionTemplate[] numericTemplates = null;

    public static final String STACK_INSTRUCTION_RESOURCE_PATH
            = "/instructions/stack";
    public static final String INSTRUCTIONS_OPERATORS_PATH
            = "/instructions/operators";
    public static final String NUMERIC_TEMPLATES_PATH
            = "/instructions/numeric";

    private FileSystem jarFileSystem = null;

    /**
     * scans the resources for templates in a given folder
     * @param PATH
     * @return
     */
    private InstructionTemplate[] scanForTemplates(final String PATH){
        InstructionTemplate[] theArrayOfTemplates = null;
        try {
            URI uri = InstructionConfigStuff.class.getResource(PATH).toURI();
            Path myPath;
            System.out.println("the thing: " + uri);
            if (uri.getScheme().equals("jar")) {
                if(jarFileSystem == null)
                    jarFileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
                System.out.println("the files:" + jarFileSystem);
                myPath = jarFileSystem.getPath(PATH);
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
            theArrayOfTemplates = templatesTemp.toArray(new InstructionTemplate[]{});

            System.out.println("after init: " + Arrays.toString(theArrayOfTemplates));
        } catch (URISyntaxException | IOException e) {
            System.out.println("caught error and printed it");
            e.printStackTrace();
        }
        return theArrayOfTemplates;
    }

    @Override
    public void init(ProcessingEnvironment env) {
        stackInstructionTemplates = scanForTemplates(STACK_INSTRUCTION_RESOURCE_PATH);
        operatorTemplates = scanForTemplates(INSTRUCTIONS_OPERATORS_PATH);
        numericTemplates = scanForTemplates(NUMERIC_TEMPLATES_PATH);

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

                GenerateStackIntructions annotationThingIdk =
                        element.getAnnotation(GenerateStackIntructions.class);

                List<? extends Element> enclosedElements =
                    element.getEnclosedElements();

                Map<GenerateStackIntructions.TemplateVariable, String> valueMapping = new HashMap<>();

//                for(Element enclosed : enclosedElements){
//                    if(!(enclosed instanceof VariableElement))continue;
//                    VariableElement var = (VariableElement) enclosed;
//
//                    String varName = var.getSimpleName().toString();
//                    String value = (String) var.getConstantValue();
//
//                    Arrays.stream(GenerateStackIntructions.TemplateVariable.values()).forEach(varThing -> {
//                        if(varThing.a.equals(varName))
//                            valueMapping.put(varThing, value);
//                    });
//                }
                valueMapping.put(
                        GenerateStackIntructions.TemplateVariable.STACK_NAME,
                        annotationThingIdk.stack_name()
                );
                valueMapping.put(
                        GenerateStackIntructions.TemplateVariable.STACK_INSTRUCTION_PATH,
                        annotationThingIdk.stack_instruction_path()
                );
                valueMapping.put(
                        GenerateStackIntructions.TemplateVariable.STACK_JAVA_TYPE,
                        annotationThingIdk.stack_java_type()
                );
                valueMapping.put(
                        GenerateStackIntructions.TemplateVariable.STACK_VM_NAME,
                        annotationThingIdk.stack_vm_name()
                );
                if(valueMapping
                        .get(GenerateStackIntructions.TemplateVariable.STACK_INSTRUCTION_PATH)
                        .isEmpty()){
                    TypeElement classElement = ((TypeElement) element);
                    valueMapping.put(
                            GenerateStackIntructions.TemplateVariable.STACK_INSTRUCTION_PATH,
                            classElement.getQualifiedName().toString()
                             .replace("." + element.getSimpleName().toString(), "")
                    );
                }

                //this branch should never enter...
                if(valueMapping.size() != GenerateStackIntructions.TemplateVariable.values().length){
                    messager.printMessage(Diagnostic.Kind.ERROR,
                            "too little or too many values. " +
                                    Arrays.toString(valueMapping.keySet().toArray())
                            );
                    return false;
                }

                boolean numeric =  annotationThingIdk.numeric();
//                System.out.println("!NUMERIC!: " + numeric);

                if(!createStackInstructions((TypeElement) element, valueMapping, numeric))
                    return false;
            }
        }

        return true;
    }

    private boolean createStackInstructions(TypeElement classElement,
                                            Map<GenerateStackIntructions.TemplateVariable, String> valueMapping,
                                            boolean numeric) {
        System.out.println("create stack instructions for " + classElement.getSimpleName());
//        File[] templates = instructionTemplateDir.listFiles();
        System.out.println(Arrays.toString(stackInstructionTemplates));
        for(InstructionTemplate instructionTemplate : stackInstructionTemplates){
            try {
                String instructionName = instructionTemplate.templateName
                        .replace(".ftl", "");//take out end thing if its there
                instructionName = valueMapping.get(GenerateStackIntructions.TemplateVariable.STACK_NAME)
                        + instructionName;

                String className =
                        valueMapping.get(GenerateStackIntructions.TemplateVariable.STACK_INSTRUCTION_PATH)
                        + ".stack." + instructionName;

                writeSourceFile(className, instructionTemplate, valueMapping);
            }catch(Exception exception){
                messager.printMessage(Diagnostic.Kind.ERROR, exception.getMessage());
            }
        }
        for(InstructionTemplate template : operatorTemplates){
            try {
                String instructionName = template.templateName
                        .replace(".ftl", "")
                        .replace("Stack", valueMapping.get(GenerateStackIntructions.TemplateVariable.STACK_NAME))
                        ;

                String className =
                        valueMapping.get(GenerateStackIntructions.TemplateVariable.STACK_INSTRUCTION_PATH)
                                + "." + instructionName;
                writeSourceFile(className, template, valueMapping);
            }catch(Exception exception){
                messager.printMessage(Diagnostic.Kind.ERROR, exception.getMessage());
            }
        }
        if (numeric) {
            for (InstructionTemplate template : numericTemplates) {
                try {
                    String instructionName = template.templateName
                            .replace(".ftl", "");
                    instructionName = valueMapping.get(GenerateStackIntructions.TemplateVariable.STACK_NAME)
                            + instructionName;

                    String className =
                            valueMapping.get(GenerateStackIntructions.TemplateVariable.STACK_INSTRUCTION_PATH)
                                    + ".numeric." + instructionName;
                    writeSourceFile(className, template, valueMapping);
                } catch (Exception exception) {
                    messager.printMessage(Diagnostic.Kind.ERROR, exception.getMessage());
                }
            }
        }
        return true;
    }

    private void writeSourceFile(String className,
                                 InstructionTemplate instructionTemplate,
                                 Map<GenerateStackIntructions.TemplateVariable,String> valueMapping) throws IOException {
        JavaFileObject javaFileObject =
                processingEnv.getFiler().createSourceFile(className);

        System.out.println("adding " + className);

        Writer writer = javaFileObject.openWriter();

        instructionTemplate.process(valueMapping, writer);

        writer.close();
        System.out.println("wrote it");
    }


}
