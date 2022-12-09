package xyz.davidpineiro.jpush.vm.instruction.integer;

public class IntMaxInstruction extends BinaryIntOperation {
    @Override
    public Integer getResult(Integer a, Integer b) {
        return Math.max(a, b);
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.intStack.push(Math.max(vm.intStack.pop(), vm.intStack.pop()));
//    }
}
