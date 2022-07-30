package me.thatshawt.jpush.instruction;

import me.thatshawt.jpush.PushVM;
import me.thatshawt.jpush.data.PushStack;

public abstract class StackClearInstruction<T> extends Instruction {

    public abstract PushStack<T> getStack(PushVM vm);

    @Override
    public void execute(PushVM vm) {
        getStack(vm).clear();
    }
}
