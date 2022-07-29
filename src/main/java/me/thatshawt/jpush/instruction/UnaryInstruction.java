package me.thatshawt.jpush.instruction;

import me.thatshawt.jpush.PushVM;
import me.thatshawt.jpush.data.PushStack;

public abstract class UnaryInstruction<T> extends Instruction{

    public abstract T getA(PushStack<T> stack);
    public abstract T execute(T a);
    public abstract PushStack<T> getStack(PushVM vm);

    @Override
    public void execute(PushVM vm) {
        PushStack<T> stack = getStack(vm);
        stack.push(execute(getA(stack)));
    }
}
