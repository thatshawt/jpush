package xyz.davidpineiro.jpush.vm.instruction;

import xyz.davidpineiro.jpush.vm.PushStack;
import xyz.davidpineiro.jpush.vm.PushVM;

import java.util.Stack;

public abstract class SweepToInstruction<T> implements Instruction{
    public abstract PushStack<T> from(PushVM vm);
    protected abstract PushStack<T> to(PushVM vm);

    protected boolean push = true;

    @Override
    public void exec(PushVM vm) {
        final int n = vm.intStack.pop();
        final int stackIndex = vm.intStack.pop();
        final Stack<T> fromStack = from(vm).getCurrentStack();
        final Stack<T> toStack = to(vm).getStack(stackIndex);
        for(int i=n;i>0;i--){
            final int currentIndex = fromStack.size() - i;
            final T val = fromStack.remove(currentIndex);
            if(push)
                toStack.push(val);
            else
                toStack.insertElementAt(val, 0);
        }
    }
}
