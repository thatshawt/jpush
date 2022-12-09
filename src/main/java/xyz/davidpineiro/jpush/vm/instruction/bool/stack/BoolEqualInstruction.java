package xyz.davidpineiro.jpush.vm.instruction.bool.stack;

import xyz.davidpineiro.jpush.vm.instruction.bool.BinaryBoolOperation;

public class BoolEqualInstruction extends BinaryBoolOperation {
    @Override
    public Boolean getResult(Boolean a, Boolean b) {
        return a == b;
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.boolStack.push(vm.boolStack.pop() == vm.boolStack.pop());
//    }
}
