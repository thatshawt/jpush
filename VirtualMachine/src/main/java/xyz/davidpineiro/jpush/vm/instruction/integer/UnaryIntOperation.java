package xyz.davidpineiro.jpush.vm.instruction.integer;

import xyz.davidpineiro.jpush.vm.PushStack;
import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;
import xyz.davidpineiro.jpush.vm.instruction.NullaryInstructionOperation;
import xyz.davidpineiro.jpush.vm.instruction.UnaryInstructionOperation;

public abstract class UnaryIntOperation extends UnaryInstructionOperation<Integer> {
    @Override
    protected PushStack<Integer> getStack(PushVM vm) {
        return vm.intStack;
    }
}
