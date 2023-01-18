package xyz.davidpineiro.jpush.vm.instruction.string;

public class StringAddInstruction extends BinaryStringOperation {

    @Override
    protected String getResult(String a, String b) {
        return a + b;
    }
}
