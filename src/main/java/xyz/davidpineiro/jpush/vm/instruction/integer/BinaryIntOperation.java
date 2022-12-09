package xyz.davidpineiro.jpush.vm.instruction.integer;

import xyz.davidpineiro.jpush.vm.PushStack;
import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.BinaryInstructionOperation;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public abstract class BinaryIntOperation extends BinaryInstructionOperation<Integer> {

    @Override
    protected PushStack<Integer> getStack(PushVM vm) {
        return vm.intStack;
    }
}
