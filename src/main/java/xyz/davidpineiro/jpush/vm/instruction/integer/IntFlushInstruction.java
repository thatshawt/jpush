package xyz.davidpineiro.jpush.vm.instruction.integer;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class IntFlushInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.intStack.empty();
    }
}
