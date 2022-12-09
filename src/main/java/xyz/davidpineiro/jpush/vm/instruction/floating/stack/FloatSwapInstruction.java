package xyz.davidpineiro.jpush.vm.instruction.floating.stack;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class FloatSwapInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        float one = vm.floatStack.pop();
        float two = vm.floatStack.pop();
        vm.floatStack.push(two);
        vm.floatStack.push(one);
    }
}
