package xyz.davidpineiro.jpush.vm.instruction.bool;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public abstract class NullaryBoolOperation implements Instruction {

    abstract boolean getResult();

    @Override
    public void exec(PushVM vm){
        //we do this regardless cus we dont have any inputs to keep or discard
        vm.boolStack.push(getResult());
    }
}
