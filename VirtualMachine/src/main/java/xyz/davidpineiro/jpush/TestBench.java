package xyz.davidpineiro.jpush;

import xyz.davidpineiro.jpush.vm.DebugPushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;
import xyz.davidpineiro.jpush.vm.instruction.floating.numeric.FloatAddInstruction;
import xyz.davidpineiro.jpush.vm.instruction.floating.stack.FloatConstantInstruction;

import java.util.Arrays;
import java.util.List;

public class TestBench {

    public static void main(String[] args) {
        DebugPushVM vm = new DebugPushVM();
        vm.verbose = true;

        List<Instruction> program1 = Arrays.asList(
                new FloatConstantInstruction(1.0f),
                new FloatConstantInstruction(2.0f),
                new FloatAddInstruction()
            );

        vm.addInstructions(program1);
        System.out.println("--------------------");
        System.out.println("before:");
        vm.printAllStacks();
        System.out.println("--------------------");

        vm.runUntilHalt();

        System.out.println("\nafter:");
        vm.printAllStacks();
        System.out.println("--------------------");

//        System.out.println(vm.intStack.toString());
    }

}
