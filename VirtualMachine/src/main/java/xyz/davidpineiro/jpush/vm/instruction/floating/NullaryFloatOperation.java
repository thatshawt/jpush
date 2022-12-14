package xyz.davidpineiro.jpush.vm.instruction.floating;

import xyz.davidpineiro.jpush.vm.PushStack;
import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.NullaryInstructionOperation;

public abstract class NullaryFloatOperation extends NullaryInstructionOperation<Float> {

    @Override
    protected PushStack<Float> getStack(PushVM vm) {
        return vm.floatStack;
    }
}
