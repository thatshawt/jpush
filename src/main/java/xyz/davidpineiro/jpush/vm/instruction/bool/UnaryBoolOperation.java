package xyz.davidpineiro.jpush.vm.instruction.bool;

import xyz.davidpineiro.jpush.vm.PushStack;
import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;
import xyz.davidpineiro.jpush.vm.instruction.UnaryInstructionOperation;

public abstract class UnaryBoolOperation extends UnaryInstructionOperation<Boolean> {
    @Override
    protected PushStack<Boolean> getStack(PushVM vm) {
        return vm.boolStack;
    }

    //    //make this true to keep the inputs of the thing
//    protected boolean keepInputs = false;
//    protected abstract boolean getResult(boolean a);
//
//    public void exec(PushVM vm){
//        if(!keepInputs)//dont keep inputs (default behavior)
//            vm.boolStack.push(getResult(vm.boolStack.pop()));
//        else//keep inputs
//            vm.boolStack.push(getResult(vm.boolStack.get(vm.boolStack.size()-1)));
//    }
}
