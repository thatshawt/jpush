package xyz.davidpineiro.jpush.vm.instruction.floating;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class FloatFromBoolInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.floatStack.push(vm.boolStack.pop() ? 1.0f : 0.0f);
    }
}
