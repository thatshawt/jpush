package xyz.davidpineiro.jpush.vm.instruction.integer;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

//Pushes 1 if the top BOOLEAN is TRUE, or 0 if the top BOOLEAN is FALSE.
public class IntFromBoolInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.intStack.push(vm.boolStack.pop() ? 1 : 0);
    }
}
