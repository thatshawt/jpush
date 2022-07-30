package me.thatshawt.jpush.instruction.bool;

import me.thatshawt.jpush.PushVM;
import me.thatshawt.jpush.data.PushStack;
import me.thatshawt.jpush.instruction.StackSizeInstruction;

public class BooleanStackSizeInstruction extends StackSizeInstruction<Boolean> {
    @Override
    public String toString() {
        return "bool.stack.size";
    }

    @Override
    public PushStack<Boolean> getStack(PushVM vm) {
        return vm.booleanStack;
    }
}
