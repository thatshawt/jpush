package xyz.davidpineiro.jpush.vm.instruction.floating;

import xyz.davidpineiro.jpush.vm.PushStack;
import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.BinaryInstructionOperation;

public abstract class BinaryFloatOperation extends BinaryInstructionOperation<Float> {
    @Override
    protected PushStack<Float> getStack(PushVM vm) {
        return vm.floatStack;
    }


    //    //make this true to keep the inputs of the thing
//    private boolean keepInputs = false;
//    abstract float getResult(float a, float b);
//
//    public void exec(PushVM vm){
//        if(!keepInputs)//dont keep inputs (default behavior)
//            vm.floatStack.push(getResult(vm.floatStack.pop(), vm.floatStack.pop()));
//        else//keep inputs
//            vm.floatStack.push(getResult(vm.floatStack.get(vm.floatStack.size()-1),
//                                        vm.floatStack.get(vm.floatStack.size()-2)));
//    }

}
