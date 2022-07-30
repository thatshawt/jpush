package me.thatshawt.jpush.instruction;

import me.thatshawt.jpush.PushVM;
import me.thatshawt.jpush.data.PushStack;

public abstract class StackSizeInstruction<T> extends Instruction{

    @Override
    public void execute(PushVM vm) {
        PushStack<T> stack = getStack(vm);
        vm.integerStack.push(stack.size());
    }

    public abstract PushStack<T> getStack(PushVM vm);
}
