package xyz.davidpineiro.jpush.vm.instruction.other;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class FlushAllStacksInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.clearDataStacks();
    }
}
