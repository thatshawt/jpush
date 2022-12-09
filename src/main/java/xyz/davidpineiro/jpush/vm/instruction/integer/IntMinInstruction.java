package xyz.davidpineiro.jpush.vm.instruction.integer;

public class IntMinInstruction extends BinaryIntOperation {
    @Override
    public Integer getResult(Integer a, Integer b) {
        return Math.min(a, b);
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.intStack.push(Math.min(vm.intStack.pop(), vm.intStack.pop()));
//    }
}
