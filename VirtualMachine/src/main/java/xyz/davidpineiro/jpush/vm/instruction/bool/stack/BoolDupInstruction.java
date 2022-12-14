package xyz.davidpineiro.jpush.vm.instruction.bool.stack;

import xyz.davidpineiro.jpush.vm.instruction.bool.UnaryBoolOperation;

public class BoolDupInstruction extends UnaryBoolOperation {
    public BoolDupInstruction() {
        this.keepInputs = true;//cus duplicate lollll
    }

    @Override
    public Boolean getResult(Boolean a) {
        return a;
    }
//    @Override
//    public void exec(PushVM vm) {
//        vm.boolStack.push(vm.boolStack.peek());
//    }
}
