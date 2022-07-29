package me.thatshawt.jpush.instruction.bool;

import me.thatshawt.jpush.PushVM;
import me.thatshawt.jpush.data.PushStack;
import me.thatshawt.jpush.instruction.BinaryInstruction;

public class BooleanXorInstruction extends BinaryInstruction<Boolean> {

    public Boolean getA(PushStack<Boolean> stack) {
        return stack.pop();
    }

    public Boolean getB(PushStack<Boolean> stack) {
        return stack.pop();
    }

    public Boolean execute(Boolean a, Boolean b) {
        return a ^ b;
    }

    public PushStack<Boolean> getStack(PushVM vm) {
        return vm.booleanStack;
    }

    public String toString() {
        return "bool.xor";
    }
}
