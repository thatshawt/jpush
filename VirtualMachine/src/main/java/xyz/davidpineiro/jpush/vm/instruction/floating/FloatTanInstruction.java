package xyz.davidpineiro.jpush.vm.instruction.floating;

public class FloatTanInstruction extends UnaryFloatOperation {
    @Override
    public Float getResult(Float a) {
        return (float)Math.tan(a);
    }

}
