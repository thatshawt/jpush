package xyz.davidpineiro.jpush.vm.instruction.floating;

public class FloatMaxInstruction extends BinaryFloatOperation {
    @Override
    float getResult(float a, float b) {
        return Math.max(a, b);
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.floatStack.push(Math.max(vm.floatStack.pop(), vm.floatStack.pop()));
//    }
}
