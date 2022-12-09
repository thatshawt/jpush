package xyz.davidpineiro.jpush.vm.instruction;

import xyz.davidpineiro.jpush.vm.PushStack;
import xyz.davidpineiro.jpush.vm.PushVM;

public abstract class UnaryInstructionOperation<T> implements Instruction{

        //make this true to keep the inputs of the thing
    protected boolean keepInputs = false;
    protected abstract T getResult(T a);
    protected abstract PushStack<T> getStack(PushVM vm);

    public void exec(PushVM vm){
        if(!keepInputs)//dont keep inputs (default behavior)
            getStack(vm).push(getResult(getStack(vm).pop()));
        else//keep inputs
            getStack(vm).push(getResult(getStack(vm).get(getStack(vm).size()-1)));
    }

}
