package xyz.davidpineiro.jpush.vm.instruction.floating;

import xyz.davidpineiro.jpush.vm.PushStack;
import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;
import xyz.davidpineiro.jpush.vm.instruction.UnaryInstructionOperation;

public abstract class UnaryFloatOperation extends UnaryInstructionOperation<Float> {

    @Override
    protected PushStack<Float> getStack(PushVM vm) {
        return vm.floatStack;
    }
}
