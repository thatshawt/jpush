package me.thatshawt.jpush.instruction;

import me.thatshawt.jpush.PushVM;
import me.thatshawt.jpush.data.PushStack;

public abstract class StackPopInstruction<T> extends Instruction {
    /**
     * if this throws an error then it will be caught and put into exception stack lmao.
     * nothing can stop <i>this</i> vm muuahahauhahu
     **/
    @Override
    public void execute(PushVM vm) {
        setStack(vm, (PushStack<T>) vm.stackStack.pop());
    }

    public abstract void setStack(PushVM vm, PushStack<T> popped);
}
