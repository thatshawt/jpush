package xyz.davidpineiro.jpush.vm.instruction.floating;

public class FloatMinInstruction extends BinaryFloatOperation {
    @Override
    public Float getResult(Float a, Float b) {
        return Math.min(a, b);
    }
    //    @Override
//    public void exec(PushVM vm) {
//        vm.floatStack.push(Math.min(vm.floatStack.pop(), vm.floatStack.pop()));
//    }
}
