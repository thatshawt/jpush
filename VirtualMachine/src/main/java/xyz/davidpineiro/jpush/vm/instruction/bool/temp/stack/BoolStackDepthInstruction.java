package xyz.davidpineiro.jpush.vm.instruction.bool.temp.stack;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class BoolStackDepthInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.intStack.push(vm.boolStack.size());
    }
}
