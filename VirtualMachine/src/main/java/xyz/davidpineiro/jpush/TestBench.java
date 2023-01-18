package xyz.davidpineiro.jpush;

import xyz.davidpineiro.jpush.vm.DebugPushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;
import xyz.davidpineiro.jpush.vm.instruction.code.CodeAssembleInstruction;
import xyz.davidpineiro.jpush.vm.instruction.code.CodeSweepInsertToExecInstruction;
import xyz.davidpineiro.jpush.vm.instruction.code.CodeSweepToExecInstruction;
import xyz.davidpineiro.jpush.vm.instruction.floating.numeric.FloatAddInstruction;
import xyz.davidpineiro.jpush.vm.instruction.floating.stack.FloatConstantInstruction;
import xyz.davidpineiro.jpush.vm.instruction.integer.stack.IntConstantInstruction;
import xyz.davidpineiro.jpush.vm.instruction.other.DebugPrintStacksInstruction;
import xyz.davidpineiro.jpush.vm.instruction.other.FlushAllStacksInstruction;
import xyz.davidpineiro.jpush.vm.instruction.string.stack.StringConstantInstruction;
import xyz.davidpineiro.jpush.vm.instruction.string.stack.StringDupInstruction;

import java.util.Arrays;
import java.util.List;

public class TestBench {

    public static void main(String[] args) {
        DebugPushVM vm = new DebugPushVM();
//        vm.verbose = true;

        List<Instruction> program1 = Arrays.asList(
                new FloatConstantInstruction(1.0f),
                new FloatConstantInstruction(2.0f),
                new FloatAddInstruction(),
                new DebugPrintStacksInstruction(),
                new StringConstantInstruction("FloatConstant 1.14;FloatConstant 2.14;FloatAdd;"),
                new IntConstantInstruction(0), // stackIndex to send it to
                new IntConstantInstruction(3), // n item to "sweep"
                new CodeAssembleInstruction(),
                new CodeSweepInsertToExecInstruction(),
                new DebugPrintStacksInstruction(),
                new StringConstantInstruction("sussy baka")
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
