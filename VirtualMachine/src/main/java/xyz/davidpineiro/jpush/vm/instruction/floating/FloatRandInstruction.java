package xyz.davidpineiro.jpush.vm.instruction.floating;

import java.util.Random;

public class FloatRandInstruction extends NullaryFloatOperation {
    private static Random random = new Random();

    @Override
    public Float getResult() {
        return random.nextFloat();
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.floatStack.push(random.nextFloat());
//    }
}
