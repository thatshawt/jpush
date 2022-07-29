package me.thatshawt.jpush.instruction.bool;

import me.thatshawt.jpush.PushVM;
import me.thatshawt.jpush.data.PushStack;
import me.thatshawt.jpush.instruction.ConstantInstruction;

public class BooleanConstantInstruction extends ConstantInstruction<Boolean> {
    public boolean val;

    public BooleanConstantInstruction(boolean val) {
        this.val = val;
    }

    public Boolean getValue() {
        return this.val;
    }

    public PushStack<Boolean> getStack(PushVM vm) {
        return vm.booleanStack;
    }

    public String toString() {
        return "bool.constant." + this.val;
    }
}
