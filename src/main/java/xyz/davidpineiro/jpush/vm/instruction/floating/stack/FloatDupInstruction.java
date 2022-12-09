package xyz.davidpineiro.jpush.vm.instruction.floating.stack;

import xyz.davidpineiro.jpush.vm.instruction.floating.UnaryFloatOperation;

public class FloatDupInstruction extends UnaryFloatOperation {
    FloatDupInstruction(){
        this.keepInputs = true;
    }

    @Override
    public Float getResult(Float a) {
        return a;
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.floatStack.push(vm.floatStack.peek());
//    }
}
