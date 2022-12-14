package xyz.davidpineiro.jpush.vm.instruction.floating.stack;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class FloatFlushInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.floatStack.clear();
    }
}
