package xyz.davidpineiro.jpush.vm;

import xyz.davidpineiro.jpush.vm.instruction.Instruction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public abstract class Operation<T> implements Instruction {

    protected abstract PushStack<T> getStack(PushVM vm);
    protected abstract T processParams(List<T> params);

    protected boolean keepInputs = false;
    private final Function<PushVM, Void> theExecFunc;

    public Operation(int args){
        this.theExecFunc = getExecFunction(args);
    }

    private Function<PushVM, Void> getExecFunction(int args){
        return (vm) -> {
            PushStack<T> stack = getStack(vm);

            ArrayList<T> params = new ArrayList<>(args);
            for(int i=0;i<args;i++){params.add(null);}
//            System.out.println(args);

            if(!keepInputs){
                for(int i=0;i<args;i++){
                    final int index = args-1 - i;
                    params.set(index,stack.pop());
                }
            }else{
                for(int i=0;i<args;i++){
                    final int index = args-1 - i;
                    params.set(index, stack.get(stack.size()-args-i));
                }
            }

            stack.push(processParams(params));

            return null;
        };
    }

    @Override
    public void exec(PushVM vm){
        theExecFunc.apply(vm);
    }

}
