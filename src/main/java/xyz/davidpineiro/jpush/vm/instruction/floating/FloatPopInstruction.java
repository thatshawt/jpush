package xyz.davidpineiro.jpush.vm.instruction.floating;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class FloatPopInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.floatStack.pop();
    }
}
