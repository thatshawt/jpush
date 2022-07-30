package me.thatshawt.jpush.instruction.bool;

import me.thatshawt.jpush.PushVM;
import me.thatshawt.jpush.data.PushStack;
import me.thatshawt.jpush.instruction.StackPushInstruction;

public class BooleanStackPushInstruction extends StackPushInstruction<Boolean> {
    @Override
    public PushStack<Boolean> getStack(PushVM vm) {
        return vm.booleanStack;
    }

    @Override
    public String toString() {
        return "bool.stack.push";
    }
}
