package xyz.davidpineiro.jpush.vm.instruction.bool.temp.stack;

import xyz.davidpineiro.jpush.vm.instruction.bool.UnaryBoolOperation;

public class BoolDupInstruction extends UnaryBoolOperation {
    public BoolDupInstruction() {
        this.keepInputs = true;//cus duplicate lollll
    }

    @Override
    public Boolean getResult(Boolean a) {
        return a;
    }
}
