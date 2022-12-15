package ${stack_instruction_path}.numeric;

import xyz.davidpineiro.jpush.vm.*;
import xyz.davidpineiro.jpush.vm.instruction.*;
import ${stack_instruction_path}.*;

public class ${stack_name}LessInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.boolStack.push(vm.${stack_vm_name}.pop() < vm.${stack_vm_name}.pop());
    }
}