package xyz.davidpineiro.jpush.vm.instruction.bool;

import xyz.davidpineiro.jpush.vm.PushStack;
import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.BinaryInstructionOperation;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public abstract class BinaryBoolOperation extends BinaryInstructionOperation<Boolean> {
    @Override
    protected PushStack<Boolean> getStack(PushVM vm) {
        return vm.boolStack;
    }

    //    //make this true to keep the inputs of the thing
//    private boolean keepInputs = false;
//    protected abstract boolean getResult(boolean a, boolean b);
//
//    public void exec(PushVM vm){
//        if(!keepInputs)//dont keep inputs (default behavior)
//            vm.boolStack.push(getResult(vm.boolStack.pop(), vm.boolStack.pop()));
//        else//keep inputs
//            vm.boolStack.push(getResult(vm.boolStack.get(vm.boolStack.size()-1),
//                                        vm.boolStack.get(vm.boolStack.size()-2)));
//    }

}
