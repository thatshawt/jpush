package xyz.davidpineiro.jpush.vm.instruction.other;

import xyz.davidpineiro.jpush.vm.DebugPushVM;
import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class DebugPrintStacksInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        if(vm instanceof DebugPushVM)
            ((DebugPushVM) vm).printAllStacks();
    }
}
