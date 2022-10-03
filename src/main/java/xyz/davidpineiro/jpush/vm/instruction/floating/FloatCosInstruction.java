package xyz.davidpineiro.jpush.vm.instruction.floating;

public class FloatCosInstruction extends UnaryFloatOperation {
    @Override
    float getResult(float a) {
        return (float)Math.cos(a);
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.floatStack.push((float) Math.cos(vm.floatStack.pop()));
//    }
}
