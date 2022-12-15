package xyz.davidpineiro.jpush.vm.instruction.bool.temp.stack;

import xyz.davidpineiro.jpush.vm.instruction.bool.BinaryBoolOperation;

public class BoolEqualInstruction extends BinaryBoolOperation {
    @Override
    public Boolean getResult(Boolean a, Boolean b) {
        return a == b;
    }
}
