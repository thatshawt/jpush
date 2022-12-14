package xyz.davidpineiro.jpush.vm.instruction;

import xyz.davidpineiro.jpush.vm.PushStack;
import xyz.davidpineiro.jpush.vm.PushVM;

public abstract class NullaryInstructionOperation<T> implements Instruction {

    protected abstract T getResult();
    protected abstract PushStack<T> getStack(PushVM vm);

    @Override
    public void exec(PushVM vm){
        //we do this regardless cus we dont have any inputs to keep or discard
        getStack(vm).push(getResult());
    }

}
