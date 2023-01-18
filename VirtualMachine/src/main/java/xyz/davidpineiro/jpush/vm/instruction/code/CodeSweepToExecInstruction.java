package xyz.davidpineiro.jpush.vm.instruction.code;

import xyz.davidpineiro.jpush.vm.PushStack;
import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;
import xyz.davidpineiro.jpush.vm.instruction.SweepToInstruction;

public class CodeSweepToExecInstruction extends SweepToInstruction<Instruction> {
    @Override
    public PushStack<Instruction> from(PushVM vm) {
        return vm.codeStack;
    }

    @Override
    protected PushStack<Instruction> to(PushVM vm) {
        return vm.execStack;
    }
}
