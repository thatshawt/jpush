package xyz.davidpineiro.jpush.vm.instruction.floating.stack;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class FloatEqualInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.boolStack.push(vm.floatStack.pop().equals(vm.floatStack.pop()));
    }
}
