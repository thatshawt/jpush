package xyz.davidpineiro.jpush.vm.instruction.bool;

import xyz.davidpineiro.jpush.vm.PushStack;
import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;
import xyz.davidpineiro.jpush.vm.instruction.NullaryInstructionOperation;

public abstract class NullaryBoolOperation extends NullaryInstructionOperation<Boolean> {

    @Override
    protected PushStack<Boolean> getStack(PushVM vm) {
        return vm.boolStack;
    }
}
