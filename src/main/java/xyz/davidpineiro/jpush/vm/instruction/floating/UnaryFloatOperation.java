package xyz.davidpineiro.jpush.vm.instruction.floating;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public abstract class UnaryFloatOperation implements Instruction {
    //make this true to keep the inputs of the thing
    protected boolean keepInputs = false;
    abstract float getResult(float a);

    public void exec(PushVM vm){
        if(!keepInputs)//dont keep inputs (default behavior)
            vm.floatStack.push(getResult(vm.floatStack.pop()));
        else//keep inputs
            vm.floatStack.push(getResult(vm.floatStack.get(vm.floatStack.size()-1)));
    }
}
