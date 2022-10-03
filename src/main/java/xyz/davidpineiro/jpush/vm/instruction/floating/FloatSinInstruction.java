package xyz.davidpineiro.jpush.vm.instruction.floating;

public class FloatSinInstruction extends UnaryFloatOperation {
    @Override
    float getResult(float a) {
        return (float)Math.sin(a);
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.floatStack.push((float) Math.sin(vm.floatStack.pop()));
//    }
}
