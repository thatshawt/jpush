package xyz.davidpineiro.jpush.vm.instruction.floating;

public class FloatAddInstruction extends BinaryFloatOperation {
    @Override
    float getResult(float a, float b) {
        return a + b;
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.floatStack.push(vm.floatStack.pop() + vm.floatStack.pop());
//    }
}
