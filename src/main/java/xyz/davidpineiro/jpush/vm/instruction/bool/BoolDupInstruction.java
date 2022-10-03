package xyz.davidpineiro.jpush.vm.instruction.bool;

public class BoolDupInstruction extends UnaryBoolOperation {
    public BoolDupInstruction() {
        this.keepInputs = true;//cus duplicate lollll
    }

    @Override
    boolean getResult(boolean a) {
        return a;
    }
//    @Override
//    public void exec(PushVM vm) {
//        vm.boolStack.push(vm.boolStack.peek());
//    }
}
