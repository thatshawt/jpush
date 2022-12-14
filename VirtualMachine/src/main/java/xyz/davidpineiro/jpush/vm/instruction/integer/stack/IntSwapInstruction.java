package xyz.davidpineiro.jpush.vm.instruction.integer.stack;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class IntSwapInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        int one = vm.intStack.pop();
        int two = vm.intStack.pop();
        vm.intStack.push(two);
        vm.intStack.push(one);
    }
}
