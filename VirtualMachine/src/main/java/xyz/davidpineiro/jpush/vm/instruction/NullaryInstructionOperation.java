package xyz.davidpineiro.jpush.vm.instruction;

import xyz.davidpineiro.jpush.vm.Operation;
import xyz.davidpineiro.jpush.vm.PushStack;
import xyz.davidpineiro.jpush.vm.PushVM;

import java.util.List;

public abstract class NullaryInstructionOperation<T> extends Operation<T> {

    public NullaryInstructionOperation() {
        super(0);
    }

    protected abstract T getResult();

    @Override
    protected T processParams(List<T> params){
        return getResult();
    }
}