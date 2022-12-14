package xyz.davidpineiro.jpush.vm.instruction.floating;

public class FloatMulInstruction  extends BinaryFloatOperation {
    @Override
    public Float getResult(Float a, Float b) {
        return a * b;
    }
//    @Override
//    public void exec(PushVM vm) {
//        vm.floatStack.push(vm.floatStack.pop() * vm.floatStack.pop());
//    }
}
