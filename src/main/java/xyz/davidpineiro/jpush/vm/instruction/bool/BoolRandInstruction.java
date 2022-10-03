package xyz.davidpineiro.jpush.vm.instruction.bool;

import java.util.Random;

public class BoolRandInstruction extends NullaryBoolOperation {
    private static Random random = new Random();

    @Override
    boolean getResult() {
        return random.nextBoolean();
    }

//    @Override
//    public void exec(PushVM vm) {
//        vm.boolStack.push(random.nextBoolean());
//    }
}
