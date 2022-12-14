package xyz.davidpineiro.jpush.vm.instruction.floating.stack;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class FloatConstantInstruction implements Instruction {

    float val;
    public FloatConstantInstruction(float val){
        this.val = val;
    }

    @Override
    public void exec(PushVM vm) {
        vm.floatStack.push(val);
    }
}
