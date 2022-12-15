package xyz.davidpineiro.jpush.vm.instruction.bool.temp.stack;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

//Inserts the top BOOLEAN "deep" in the stack, at the position
//	indexed by the top INTEGER. the top bool and int are both popped.
public class BoolShoveInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.boolStack.insertElementAt(vm.boolStack.pop(), vm.boolStack.size()-1-vm.intStack.pop());
    }
}
