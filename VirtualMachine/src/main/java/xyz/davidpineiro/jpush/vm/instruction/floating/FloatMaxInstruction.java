package xyz.davidpineiro.jpush.vm.instruction.floating;

public class FloatMaxInstruction extends BinaryFloatOperation {
    @Override
    public Float getResult(Float a, Float b) {
        return Math.max(a, b);
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.floatStack.push(Math.max(vm.floatStack.pop(), vm.floatStack.pop()));
//    }
}
