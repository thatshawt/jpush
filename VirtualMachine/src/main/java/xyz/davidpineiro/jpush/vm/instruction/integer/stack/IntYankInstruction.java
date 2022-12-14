package xyz.davidpineiro.jpush.vm.instruction.integer.stack;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class IntYankInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.intStack.push(vm.intStack.remove(vm.intStack.size()-1-vm.intStack.pop()));
    }
}
