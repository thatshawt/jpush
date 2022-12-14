package xyz.davidpineiro.jpush.vm.instruction.integer;

public class IntMulInstruction extends BinaryIntOperation {
    @Override
    public Integer getResult(Integer a, Integer b) {
        return a * b;
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.intStack.push(vm.intStack.pop() * vm.intStack.pop());
//    }
}
