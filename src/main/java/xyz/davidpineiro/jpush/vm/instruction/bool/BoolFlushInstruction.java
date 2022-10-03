package xyz.davidpineiro.jpush.vm.instruction.bool;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class BoolFlushInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.boolStack.empty();
    }
}
