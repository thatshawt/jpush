package xyz.davidpineiro.jpush.vm.instruction.bool;

public class BoolAndInstruction extends BinaryBoolOperation {

    @Override
    boolean getResult(boolean a, boolean b) {
        return a && b;
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.boolStack.push(vm.boolStack.pop() && vm.boolStack.pop());
//    }
}
