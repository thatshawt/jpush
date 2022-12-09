package xyz.davidpineiro.jpush.vm.instruction.bool.stack;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

// Removes an indexed item from "deep" in the stack and pushes
// it on top of the stack. The index is taken from the INTEGER stack.
public class BoolYankInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.boolStack.push(vm.boolStack.remove(vm.boolStack.size()-1-vm.intStack.pop()));
    }
}
