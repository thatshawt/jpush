package xyz.davidpineiro.jpush.vm.instruction.bool;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

//Pushes a copy of an indexed item "deep" in the stack onto the
//	top of the stack, without removing the deep item. The index is
//	taken from the INTEGER stack.
public class BoolYankDupInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.boolStack.push(vm.boolStack.get(vm.boolStack.size()-1-vm.intStack.pop()));
    }
}
