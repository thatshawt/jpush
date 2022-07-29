package me.thatshawt.jpush.instruction.bool;

import me.thatshawt.jpush.PushVM;
import me.thatshawt.jpush.data.PushStack;
import me.thatshawt.jpush.instruction.UnaryInstruction;

public class BooleanNotInstruction extends UnaryInstruction<Boolean> {

    public String toString() {
        return "bool.not";
    }

    public Boolean getA(PushStack<Boolean> stack) {
        return stack.pop();
    }

    public Boolean execute(Boolean a) {
        return !a;
    }

    public PushStack<Boolean> getStack(PushVM vm) {
        return vm.booleanStack;
    }
}
