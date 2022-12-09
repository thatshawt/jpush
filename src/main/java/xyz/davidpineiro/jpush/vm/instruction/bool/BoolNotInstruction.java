package xyz.davidpineiro.jpush.vm.instruction.bool;

public class BoolNotInstruction extends UnaryBoolOperation {
    @Override
    protected Boolean getResult(Boolean a) {
        return !a;
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.boolStack.push(!vm.boolStack.pop());
//    }
}
