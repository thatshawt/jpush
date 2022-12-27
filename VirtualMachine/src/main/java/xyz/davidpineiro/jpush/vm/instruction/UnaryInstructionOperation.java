package xyz.davidpineiro.jpush.vm.instruction;

import xyz.davidpineiro.jpush.vm.Operation;
import xyz.davidpineiro.jpush.vm.PushStack;
import xyz.davidpineiro.jpush.vm.PushVM;

import java.util.List;

public abstract class UnaryInstructionOperation<T> extends Operation<T> {

    public UnaryInstructionOperation() {
        super(1);
    }

    protected abstract T getResult(T a);

    @Override
    protected T processParams(List<T> params){
        return getResult(params.get(0));
    }
}