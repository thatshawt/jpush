package xyz.davidpineiro.jpush.vm.instruction.bool;

public class BoolNotInstruction extends UnaryBoolOperation {
    @Override
    boolean getResult(boolean a) {
        return !a;
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.boolStack.push(!vm.boolStack.pop());
//    }
}
