package xyz.davidpineiro.jpush.vm.instruction.integer.stack;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

//Rotates the top three items on the INTEGER stack, pulling the third item out and
// pushing it on top. This is equivalent to "2
//YANK".
public class IntRotInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.intStack.push(vm.intStack.remove(vm.intStack.size()-3));
    }
}
