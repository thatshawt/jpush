package xyz.davidpineiro.jpush.vm.instruction.bool;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class BoolPopInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.boolStack.pop();
    }
}
