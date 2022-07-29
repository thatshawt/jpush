package me.thatshawt.jpush.instruction;

import me.thatshawt.jpush.PushVM;
import me.thatshawt.jpush.data.PushStack;

public abstract class ConstantInstruction<T> extends Instruction{

    public abstract T getValue();
    public abstract PushStack<T> getStack(PushVM vm);

    @Override
    public void execute(PushVM vm) {
        PushStack<T> stack = getStack(vm);
        stack.push(getValue());
    }
}
