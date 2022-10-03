package xyz.davidpineiro.jpush.vm.instruction.integer;

public class IntMulInstruction extends BinaryIntOperation {
    @Override
    int getResult(int a, int b) {
        return a * b;
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.intStack.push(vm.intStack.pop() * vm.intStack.pop());
//    }
}
