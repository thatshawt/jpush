package xyz.davidpineiro.jpush.vm.instruction;

import xyz.davidpineiro.jpush.vm.PushStack;
import xyz.davidpineiro.jpush.vm.PushVM;

public abstract class BinaryInstructionOperation<T> implements Instruction {

    //make this true to keep the inputs of the thing
    private boolean keepInputs = false;

    protected abstract T getResult(T a, T b);
    protected abstract PushStack<T> getStack(PushVM vm);

    public void exec(PushVM vm){
        PushStack<T> stack = getStack(vm);
        if(!keepInputs)//dont keep inputs (default behavior)
            stack.push(getResult(stack.pop(), stack.pop()));
        else//keep inputs
            stack.push(getResult(stack.get(stack.size()-1),
                    stack.get(stack.size()-2)));
    }

}
