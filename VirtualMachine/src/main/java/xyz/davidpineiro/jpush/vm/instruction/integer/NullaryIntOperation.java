package xyz.davidpineiro.jpush.vm.instruction.integer;

import xyz.davidpineiro.jpush.vm.PushStack;
import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;
import xyz.davidpineiro.jpush.vm.instruction.NullaryInstructionOperation;

public abstract class NullaryIntOperation extends NullaryInstructionOperation<Integer> {

    @Override
    protected PushStack<Integer> getStack(PushVM vm) {
        return vm.intStack;
    }
}
