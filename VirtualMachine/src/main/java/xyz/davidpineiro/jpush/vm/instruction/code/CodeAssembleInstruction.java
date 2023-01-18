package xyz.davidpineiro.jpush.vm.instruction.code;

import xyz.davidpineiro.jpush.assembly.PushAssembler;
import xyz.davidpineiro.jpush.vm.PushVM;
import xyz.davidpineiro.jpush.vm.instruction.Instruction;

public class CodeAssembleInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        final Instruction[] instructions = PushAssembler.assemble(vm.stringStack.pop());
        for(int i=instructions.length-1;i>=0;i--) {
            Instruction instruction = instructions[i];
            vm.codeStack.push(instruction);
        }
    }
}
