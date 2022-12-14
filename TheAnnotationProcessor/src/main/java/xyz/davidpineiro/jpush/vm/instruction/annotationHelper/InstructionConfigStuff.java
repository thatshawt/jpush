package xyz.davidpineiro.jpush.vm.instruction.annotationHelper;

import com.google.auto.service.AutoService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.io.IOUtils;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ExecutableType;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
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

    InstructionTemplate[] templates = null;
    Configuration freeMarkerConfig;
    Messager messager;

    public static final String VAR_STACK_NAME = "stack_name";
    public static final String VAR_STACK_PACKAGE_NAME = "stack_package_name";
    public static final String VAR_STACK_JAVA_TYPE = "stack_java_type";
    public static final String VAR_STACK_VM_NAME = "stack_vm_name";
    public static final String VAR_STACK_INSTRUCTION_PATH = "stack_instruction_path";
    public static final int VAR_SIZE = 5;

    public static final String INSTRUCTION_RESOURCE_PATH = "/instructions";



    @Override
    public void init(ProcessingEnvironment env) {
        freeMarkerConfig = new Configuration(Configuration.VERSION_2_3_29);
        try {
            URI uri = InstructionConfigStuff.class.getResource("/instructions").toURI();
            Path myPath;
            System.out.println("the thing: " + uri);
            if (uri.getScheme().equals("jar")) {
                FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
                System.out.println("the files:" + fileSystem);
                myPath = fileSystem.getPath("");
            } else {
                System.out.println("sussy baka");
                myPath = Paths.get(uri);
            }
            System.out.println("after the fact...");
            Stream<Path> walk = Files.walk(myPath, 1);
            List<InstructionTemplate> templatesTemp = new ArrayList<>();
            for (Iterator<Path> it = walk.iterator(); it.hasNext(); ) {
                Path path = it.next();
                if(path.toString().endsWith("instruction") || !path.toString().endsWith(".ftl"))continue;
                System.out.println(path);

                InputStream in = Files.newInputStream(path);
                final String templateData = IOUtils.toString(in, Charset.defaultCharset());
                final String templateName = path.getFileName().toString();
                templatesTemp.add(new InstructionTemplate(templateName, templateData));
            }
            templates = templatesTemp.toArray(new InstructionTemplate[0]);

            System.out.println("after init: " + Arrays.toString(templates));
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

                Map<String, String> valueMapping = new HashMap<>();

                for(Element enclosed : enclosedElements){
                    if(!(enclosed instanceof VariableElement))continue;
                    VariableElement var = (VariableElement) enclosed;

                    String varName = var.getSimpleName().toString();
                    String value = (String) var.getConstantValue();

                    switch(varName){
                        case VAR_STACK_JAVA_TYPE:
                        case VAR_STACK_PACKAGE_NAME:
                        case VAR_STACK_VM_NAME:
                        case VAR_STACK_NAME:
                        case VAR_STACK_INSTRUCTION_PATH:
                            valueMapping.put(varName, value);
                            break;
                        default:
                            break;
                    }
                }
                if(valueMapping.size() != VAR_SIZE){
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

        //return doBuilderExample(annotations, roundEnv);
    }

    private boolean createStackInstructions(TypeElement classElement, Map<String, String> valueMapping) {
        System.out.println("create stack instructions for " + classElement.getSimpleName());
//        File[] templates = instructionTemplateDir.listFiles();
        System.out.println(Arrays.toString(templates));
        for(InstructionTemplate instructionTemplate : templates){

            try {
//                Template template = freeMarkerConfig.getTemplate(instructionTemplate.templateName);
//                String template = instructionTemplate.templateString;

                String instructionName = instructionTemplate.templateName
                        .replace(".ftl", "")
                        .replace("Stack", valueMapping.get(VAR_STACK_NAME));

                //${stack_instruction_path}.${stack_package_name}.stack
                String className = valueMapping.get(VAR_STACK_INSTRUCTION_PATH) + "."
                        + valueMapping.get(VAR_STACK_PACKAGE_NAME) + ".stack." + instructionName;

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

    private boolean doBuilderExample(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv){
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements
                    = roundEnv.getElementsAnnotatedWith(annotation);

            Map<Boolean, List<Element>> annotatedMethods = annotatedElements.stream().collect(
                    Collectors.partitioningBy(element ->
                            ((ExecutableType) element.asType()).getParameterTypes().size() == 1
                                    && element.getSimpleName().toString().startsWith("set")));
//            annotatedElements.forEach(element -> element.);

            List<Element> setters = annotatedMethods.get(true);
            List<Element> otherMethods = annotatedMethods.get(false);

            otherMethods.forEach(element ->
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                            "@BuilderProperty must be applied to a setXxx method "
                                    + "with a single argument", element));

            if (setters.isEmpty()) {
                continue;
            }

            String className = ((TypeElement) setters.get(0)
                    .getEnclosingElement()).getQualifiedName().toString();

            Map<String, String> setterMap = setters.stream().collect(Collectors.toMap(
                    setter -> setter.getSimpleName().toString(),
                    setter -> ((ExecutableType) setter.asType())
                            .getParameterTypes().get(0).toString()
            ));

            try {
                writeBuilderFile(className, setterMap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    private void writeBuilderFile(
            String className, Map<String, String> setterMap
            )
            throws IOException {

        String packageName = null;
        int lastDot = className.lastIndexOf('.');
        if (lastDot > 0) {
            packageName = className.substring(0, lastDot);
        }

        String simpleClassName = className.substring(lastDot + 1);
        String builderClassName = className + "Builder";
        String builderSimpleClassName = builderClassName
                .substring(lastDot + 1);

        JavaFileObject builderFile = processingEnv.getFiler()
                .createSourceFile(builderClassName);

        try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {

            if (packageName != null) {
                out.print("package ");
                out.print(packageName);
                out.println(";");
                out.println();
            }

            out.print("public class ");
            out.print(builderSimpleClassName);
            out.println(" {");
            out.println();

            out.print("    private ");
            out.print(simpleClassName);
            out.print(" object = new ");
            out.print(simpleClassName);
            out.println("();");
            out.println();

            out.print("    public ");
            out.print(simpleClassName);
            out.println(" build() {");
            out.println("        return object;");
            out.println("    }");
            out.println();

            setterMap.entrySet().forEach(setter -> {
                String methodName = setter.getKey();
                String argumentType = setter.getValue();

                out.print("    public ");
                out.print(builderSimpleClassName);
                out.print(" ");
                out.print(methodName);

                out.print("(");

                out.print(argumentType);
                out.println(" value) {");
                out.print("        object.");
                out.print(methodName);
                out.println("(value);");
                out.println("        return this;");
                out.println("    }");
                out.println();
            });

            out.println("}");
        }
    }

//    @StackInstruction

}
