package xyz.davidpineiro.jpush.assembly;

import org.antlr.v4.runtime.*;
import xyz.davidpineiro.jpush.vm.DebugPushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Modifier;
import java.util.*;

public final class PushAssembler {

    private static final Class instructionClass;
    public static final Map<String, Class> instructionMap;

    static{
        Class instructionClass1;
        try {
            instructionClass1 = Class.forName("xyz.davidpineiro.jpush.vm.instruction.Instruction");
        } catch (ClassNotFoundException e) {
            instructionClass1 = null;
        }
        instructionClass = instructionClass1;

        Map<String, Class> map = loadStandardInstructions();

        instructionMap = Collections.unmodifiableMap(map);
    }

    private static Map<String, Class> loadStandardInstructions(){
        Set<Class> classes = new HashSet<>();
        classes.addAll(findAllClassesUsingClassLoader(
                "xyz.davidpineiro.jpush.vm.instruction"
        ));

        //add all the instructions to the map
        return addInstructions(classes);
    }

    public static Map<String, Class> addInstructions(Iterable<Class> classes){
        Map<String, Class> instructionMap = new HashMap<>();
        for(Class clazz : classes){
//                System.out.printf("%s:%s | \n", clazz.getName(), clazz.getModifiers());
            if(instructionClass.isAssignableFrom(clazz) &&
                    !Modifier.isAbstract(clazz.getModifiers())){
                String fullName = clazz.getSimpleName();

                //e.g "IntDupInstruction" -> "IntDup"
                String truncated = fullName.substring(
                        0, fullName.length() - ("Instruction".length())
                );
//                System.out.printf("YES %s\n", truncated);
                instructionMap.put(truncated.toLowerCase(), clazz);
            }else{
//                System.out.printf("NO  %s\n", clazz.getSimpleName());
            }
        }
        return instructionMap;
    }

    /**
     add two numbers program example:
     <code><br>
     IntConstant 1<br>
     IntConstant 2<br>
     IntAdd<br>
     </code><br>


    boolean logic program example:
     <code><br>
     BoolConstant true<br>
     BoolConstant false<br>
     BoolAnd<br>
     </code><br>

     general program format:
     <code><br>
     instruction1 arg0 arg1 arg2 ... argn<br>
     instruction2 arg0 arg1 arg2 ... argn<br>
     instruction2 arg0 arg1 arg2 ... argn<br>
     ...<br>
     instructionn arg0 arg1 arg2 ... argn<br>
     </code>

     */

    public static Instruction[] assemble(String program) {
        xyz.davidpineiro.jpush.assembly.PushAssemblerLexer lexer = new xyz.davidpineiro.jpush.assembly.PushAssemblerLexer(CharStreams.fromString(program));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        xyz.davidpineiro.jpush.assembly.PushAssemblerParser parser = new xyz.davidpineiro.jpush.assembly.PushAssemblerParser(tokenStream);
        AssemblerAntlrVisitor visitor = new AssemblerAntlrVisitor();
        List<Object> objects = visitor.visitStart(parser.start());
        System.out.println(objects);
        return objects.toArray(new Instruction[]{});
    }


    /*
    https://www.baeldung.com/java-find-all-classes-in-package
    modified from above so it can recursively find all classes inside packages
     */
    private static Set<Class> findAllClassesUsingClassLoader(String packageName) {
        Set<Class> instructions = new HashSet<>();
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        reader.lines().forEach(line -> {
            if(line.endsWith(".class")){//its a class
                instructions.add(getClass(line, packageName));
            }else{//i hope its a package
                instructions.addAll(findAllClassesUsingClassLoader(
                        packageName + "." + line
                ));
            }
        });

        return instructions;
    }

    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

    public static void main(String[] args){
        try {
            String programtext =
                    "DebugPrintStacks\n"
                    + "IntConstant 1\n"
                    + "IntConstant 2\n"
                    + "IntConstant 3\n"
                    + "IntConstant 4\n"
                    + "IntConstant 5\n"
                    + "IntConstant 6\n"
                    + "IntConstant 7\n"
                    + "IntConstant 1\n"
                    + "IntConstant 5\n"
                    + "DebugPrintStacks\n"
                    + "IntSweepTo\n"
                    + "DebugPrintStacks\n"
                    + "IntConstant 1\n"
                    + "IntStackIndexSet\n"
                    + "DebugPrintStacks\n"
                    + "StringConstant \"FloatConstant 3.123245;FloatConstant 1.2345;FloatAdd;\"\n"
                    + "CodeAssemble\n"
                    + "DebugPrintStacks\n"
                    + "IntConstant 0\n"
                    + "IntConstant 3\n"
                    + "CodeSweepInsertToExec\n"
                    + "DebugPrintStacks\n"
                    ;

            Instruction[] programMine = assemble(programtext);

            System.out.printf("\n\nprogram length: %d\nprogram: %s\n",
                    programMine.length, Arrays.toString(programMine));

            DebugPushVM vm = new DebugPushVM();
            vm.verbose = true;
            System.out.println();
            vm.addInstructions(List.of(programMine));
            vm.runUntilHalt();
            vm.printAllStacks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
