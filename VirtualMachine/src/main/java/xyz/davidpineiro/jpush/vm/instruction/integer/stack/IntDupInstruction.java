package xyz.davidpineiro.jpush.vm.instruction.integer.stack;

import xyz.davidpineiro.jpush.vm.instruction.integer.UnaryIntOperation;

public class IntDupInstruction extends UnaryIntOperation {
    IntDupInstruction(){
        this.keepInputs = true;
    }

    @Override
    public Integer getResult(Integer a) {
        return a;
    }


//    @Override
//    public void exec(PushVM vm) {
//        vm.intStack.push(vm.intStack.peek());
//    }
}
