package xyz.davidpineiro.jpush.vm.instruction.floating;

public class FloatTanInstruction extends UnaryFloatOperation {
    @Override
    float getResult(float a) {
        return (float)Math.tan(a);
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.floatStack.push((float) Math.tan(vm.floatStack.pop()));
//    }
}
