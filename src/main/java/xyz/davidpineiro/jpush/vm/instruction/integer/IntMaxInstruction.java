package xyz.davidpineiro.jpush.vm.instruction.integer;

public class IntMaxInstruction extends BinaryIntOperation {
    @Override
    int getResult(int a, int b) {
        return Math.max(a, b);
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.intStack.push(Math.max(vm.intStack.pop(), vm.intStack.pop()));
//    }
}
