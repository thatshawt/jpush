package xyz.davidpineiro.jpush.vm.instruction.integer;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class IntStackDepthInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.intStack.push(vm.intStack.size());
    }
}
