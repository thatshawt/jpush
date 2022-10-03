package xyz.davidpineiro.jpush.vm.instruction.integer;

public class IntDupInstruction extends UnaryIntOperation {
    IntDupInstruction(){
        this.keepInputs = true;
    }

    @Override
    int getResult(int a) {
        return a;
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.intStack.push(vm.intStack.peek());
//    }
}
