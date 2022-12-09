package xyz.davidpineiro.jpush.vm.instruction.integer.stack;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class IntPopInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.intStack.pop();
    }
}
