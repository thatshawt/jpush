package xyz.davidpineiro.jpush.vm.instruction.bool.temp.stack;

import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

//rotates top 3 items
//equivalent to 2 boolYank
public class BoolRotInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.boolStack.push(vm.boolStack.remove(vm.boolStack.size()-3));
    }
}
