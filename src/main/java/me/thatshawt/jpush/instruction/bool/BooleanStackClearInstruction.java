package me.thatshawt.jpush.instruction.bool;

import me.thatshawt.jpush.PushVM;
import me.thatshawt.jpush.data.PushStack;
import me.thatshawt.jpush.instruction.StackClearInstruction;

public class BooleanStackClearInstruction extends StackClearInstruction<Boolean> {
    @Override
    public PushStack<Boolean> getStack(PushVM vm) {
        return vm.booleanStack;
    }

    @Override
    public String toString() {
        return "bool.stack.clear";
    }
}
