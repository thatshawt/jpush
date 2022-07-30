package me.thatshawt.jpush.instruction;

import me.thatshawt.jpush.PushVM;
import me.thatshawt.jpush.data.PushStack;

public abstract class StackIndexChangeInstruction<T> extends Instruction{

    @Override
    public void execute(PushVM vm) {
        PushStack<T> stack = getStack(vm);
        stack.index = vm.integerStack.pop();
    }

    public abstract PushStack<T> getStack(PushVM vm);
}
