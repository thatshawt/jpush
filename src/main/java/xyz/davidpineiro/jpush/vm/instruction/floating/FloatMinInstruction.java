package xyz.davidpineiro.jpush.vm.instruction.floating;

public class FloatMinInstruction extends BinaryFloatOperation {
    @Override
    float getResult(float a, float b) {
        return Math.min(a, b);
    }
    //    @Override
//    public void exec(PushVM vm) {
//        vm.floatStack.push(Math.min(vm.floatStack.pop(), vm.floatStack.pop()));
//    }
}
