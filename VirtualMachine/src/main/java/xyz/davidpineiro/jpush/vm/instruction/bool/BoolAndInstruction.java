package xyz.davidpineiro.jpush.vm.instruction.bool;

public class BoolAndInstruction extends BinaryBoolOperation {

    @Override
    protected Boolean getResult(Boolean a, Boolean b) {
        return a && b;
    }

}
