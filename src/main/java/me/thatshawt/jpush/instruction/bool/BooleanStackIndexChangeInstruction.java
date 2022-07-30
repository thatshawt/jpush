package me.thatshawt.jpush.instruction.bool;

import me.thatshawt.jpush.PushVM;
import me.thatshawt.jpush.data.PushStack;
import me.thatshawt.jpush.instruction.StackIndexChangeInstruction;

public class BooleanStackIndexChangeInstruction extends StackIndexChangeInstruction<Boolean> {
    @Override
    public String toString() {
        return "bool.stack.index.change";
    }

    @Override
    public PushStack<Boolean> getStack(PushVM vm) {
        return vm.booleanStack;
    }
}
