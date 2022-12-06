package xyz.davidpineiro.jpush.assembly;

import xyz.davidpineiro.jpush.assembly.exception.InstructionNotFoundException;
import xyz.davidpineiro.jpush.vm.DebugPushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public final class PushAssembler {

    private static final Class instructionClass;
    private static final Map<String, Class> instructionMap = new HashMap<>();

    static{
        Class instructionClass1;
        try {
            instructionClass1 = Class.forName("xyz.davidpineiro.jpush.vm.instruction.Instruction");
        } catch (ClassNotFoundException e) {
            instructionClass1 = null;
        }
        instructionClass = instructionClass1;

        Set<Class> boolOps = findAllClassesUsingClassLoader(
                "xyz.davidpineiro.jpush.vm.instruction.bool"
        );
        Set<Class> floatOps = findAllClassesUsingClassLoader(
                "xyz.davidpineiro.jpush.vm.instruction.floating"
        );
        Set<Class> intOps = findAllClassesUsingClassLoader(
                "xyz.davidpineiro.jpush.vm.instruction.integer"
        );
        Set<Class> classes = new HashSet<>();
        classes.addAll(boolOps);
        classes.addAll(floatOps);
        classes.addAll(intOps);

//            Class clazz = Class.forName("xyz.davidpineiro.jpush.vm.instruction.bool.BoolConstantInstruction");

        for(Class clazz : classes){
//                System.out.printf("%s | ", clazz.getModifiers());
            if(instructionClass.isAssignableFrom(clazz) &&
                    !Modifier.isAbstract(clazz.getModifiers())){
                String fullName = clazz.getSimpleName();
                String truncated = fullName.substring(
                        0, fullName.length() - ("Instruction".length())
                );
                System.out.printf("YES %s\n", truncated);
                instructionMap.put(truncated.toLowerCase(), clazz);
            }else{
                System.out.printf("NO  %s\n", clazz.getSimpleName());
            }
        }
    }

    public static void main(String[] args){
        try {
            Instruction[] program = assemble("IntConstant 1\n" +
                    "IntConstant 2\n" +
                    "IntAdd");

            System.out.printf("\n\nprogram length: %d\nprogram: %s\n", program.length, Arrays.toString(program));

            DebugPushVM vm = new DebugPushVM();
            vm.printAllStacks();
            vm.addInstructions(List.of(program));
            vm.runUntilHalt();
            vm.printAllStacks();

//            System.out.println(classes);
//            System.out.println(clazz.getConstructors()[0].newInstance((Boolean)true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    add two numbers program:
    "
    IntConstant 1
    IntConstant 2
    IntAdd
    "

    truth gate program:
    "
    BoolConstant true
    BoolConstant false
    BoolAnd
    "

    format of a program:
    "
    instruction1 arg0 arg1 arg2 ... argn
    instruction2 arg0 arg1 arg2 ... argn
    instruction2 arg0 arg1 arg2 ... argn
    ...
    instructionn arg0 arg1 arg2 ... argn
    "

     */
    public static Instruction[] assemble(String program) throws InstructionNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Instruction> instructionList = new ArrayList<>();

        for(String line : program.split("\n")){
            String[] parts = line.split("\\s+");
            Class clazz = instructionMap.get(parts[0].toLowerCase());
            if(clazz != null){
                List<Object> args = new ArrayList<>();
                for(int i=1;i<parts.length;i++){//loop through args
                    String arg = parts[i];
                    if(arg.equalsIgnoreCase("true")
                      || arg.equalsIgnoreCase("false")){//boolean
                        args.add(Boolean.parseBoolean(arg));
                    }else if(arg.contains(".")){//float
                        args.add(Float.parseFloat(arg));
                    }else{//int ?i hope?
                        args.add(Integer.parseInt(arg));
                    }
                }

                instructionList.add((Instruction)
                        clazz.getConstructors()[0].newInstance(args.toArray()));
            }else{
                throw new InstructionNotFoundException();
            }
        }
        return instructionList.toArray(new Instruction[]{});
    }

    /*
    https://www.baeldung.com/java-find-all-classes-in-package
     */
    private static Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
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
    
}
