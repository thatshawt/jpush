package xyz.davidpineiro.jpush.vm.instruction.bool;

public class BoolNotInstruction extends UnaryBoolOperation {
    @Override
    protected Boolean getResult(Boolean a) {
        return !a;
    }

}
