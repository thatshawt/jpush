package xyz.davidpineiro.jpush.vm.instruction.bool.stack;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class BoolSwapInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        boolean one = vm.boolStack.pop();
        boolean two = vm.boolStack.pop();
        vm.boolStack.push(two);
        vm.boolStack.push(one);
    }
}
