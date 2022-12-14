package xyz.davidpineiro.jpush.vm.instruction.bool;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

//Pushes FALSE if the top INT is 0.0, or TRUE otherwise.
public class BoolFromIntInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.boolStack.push(vm.intStack.peek() != 0.0);
    }
}
