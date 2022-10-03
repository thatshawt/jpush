package xyz.davidpineiro.jpush.vm.instruction.floating;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public abstract class NullaryFloatOperation implements Instruction {

    abstract float getResult();

    @Override
    public void exec(PushVM vm){
        //we do this regardless cus we dont have any inputs to keep or discard
        vm.floatStack.push(getResult());
    }
}
