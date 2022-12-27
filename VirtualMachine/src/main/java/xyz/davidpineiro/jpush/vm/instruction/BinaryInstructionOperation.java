package xyz.davidpineiro.jpush.vm.instruction;

import xyz.davidpineiro.jpush.vm.Operation;
import xyz.davidpineiro.jpush.vm.PushStack;
import xyz.davidpineiro.jpush.vm.PushVM;

import java.util.List;

public abstract class BinaryInstructionOperation<T> extends Operation<T> {

    public BinaryInstructionOperation() {
        super(2);
    }

    protected abstract T getResult(T a, T b);

    @Override
    protected T processParams(List<T> params){
        return getResult(params.get(0), params.get(1));
    }
}
