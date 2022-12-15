package xyz.davidpineiro.jpush.vm.instruction.bool;

//import xyz.davidpineiro.jpush.vm.instruction.bool.temp.BinaryBoolOperation;

public class BoolAndInstruction extends BinaryBoolOperation {

    @Override
    protected Boolean getResult(Boolean a, Boolean b) {
        return a && b;
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.boolStack.push(vm.boolStack.pop() && vm.boolStack.pop());
//    }
}
