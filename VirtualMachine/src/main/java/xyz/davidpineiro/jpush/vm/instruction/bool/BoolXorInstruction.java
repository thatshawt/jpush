package xyz.davidpineiro.jpush.vm.instruction.bool;

public class BoolXorInstruction extends BinaryBoolOperation {
    @Override
    protected Boolean getResult(Boolean a, Boolean b) {
        return a ^ b;
    }
}
