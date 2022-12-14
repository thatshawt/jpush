package xyz.davidpineiro.jpush.vm.instruction.floating.stack;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class FloatYankDupInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.floatStack.push(vm.floatStack.get(vm.floatStack.size()-1-vm.intStack.pop()));
    }
}
