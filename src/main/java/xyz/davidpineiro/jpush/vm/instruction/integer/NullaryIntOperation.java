package xyz.davidpineiro.jpush.vm.instruction.integer;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public abstract class NullaryIntOperation implements Instruction {

    abstract int getResult();

    @Override
    public void exec(PushVM vm){
        //we do this regardless cus we dont have any inputs to keep or discard
        vm.intStack.push(getResult());
    }
}