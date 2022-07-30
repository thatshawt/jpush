package me.thatshawt.jpush.instruction.bool;

import me.thatshawt.jpush.PushVM;
import me.thatshawt.jpush.data.PushStack;
import me.thatshawt.jpush.instruction.StackPopInstruction;

public class BooleanStackPopInstruction extends StackPopInstruction<Boolean> {
    @Override
    public String toString() {
        return "bool.stack.pop";
    }

    @Override
    public void setStack(PushVM vm, PushStack<Boolean> popped) {
        vm.booleanStack = popped;
    }
}
