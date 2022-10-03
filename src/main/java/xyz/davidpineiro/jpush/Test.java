package xyz.davidpineiro.jpush;

import xyz.davidpineiro.jpush.vm.DebugPushVM;
import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;
import xyz.davidpineiro.jpush.vm.instruction.floating.FloatAddInstruction;
import xyz.davidpineiro.jpush.vm.instruction.floating.FloatConstantInstruction;

import java.util.Arrays;
import java.util.List;

/*
TODO in order:
-do programming language part
-complete the bool tests
-add float, int tests
 */
public class Test {

    public static void main(String[] args) {
        DebugPushVM vm = new DebugPushVM();
        vm.verbose = true;

        List<Instruction> program1 = Arrays.asList(
                new FloatConstantInstruction(1.0f),
                new FloatConstantInstruction(2.0f),
                new FloatAddInstruction()
                );

        vm.addInstructions(program1);
        System.out.println("before:");
        vm.printAllStacks();

        vm.runUntilHalt(true);//i like how i just have a method called runUntilHalt() like i can defeat the halting problem

        System.out.println("after:");
        vm.printAllStacks();
//        System.out.println(vm.intStack.toString());
    }

}
