package xyz.davidpineiro.jpush.vm.instruction.bool.stack;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;
//bool, Bool,
public class BoolConstantInstruction implements Instruction {

    boolean val;

    public BoolConstantInstruction(boolean val) {
        this.val = val;
    }

    @Override
    public void exec(PushVM vm) {
        vm.boolStack.push(val);
    }
}