package xyz.davidpineiro.jpush.vm.instruction.integer;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class IntConstantInstruction implements Instruction {

    int val;
    public IntConstantInstruction(int val){
        this.val = val;
    }

    @Override
    public void exec(PushVM vm) {
        vm.intStack.push(val);
    }
}
