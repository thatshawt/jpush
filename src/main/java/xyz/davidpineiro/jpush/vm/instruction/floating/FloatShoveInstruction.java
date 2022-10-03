package xyz.davidpineiro.jpush.vm.instruction.floating;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class FloatShoveInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.floatStack.insertElementAt(vm.floatStack.pop(), vm.floatStack.size()-1-vm.intStack.pop());
    }
}
