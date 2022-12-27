package ${stack_instruction_path}.stack;

import xyz.davidpineiro.jpush.vm.*;
import xyz.davidpineiro.jpush.vm.instruction.*;
import ${stack_instruction_path}.*;

public class ${stack_name}SwapInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        ${stack_java_type} one = vm.${stack_vm_name}.pop();
        ${stack_java_type} two = vm.${stack_vm_name}.pop();
        vm.${stack_vm_name}.push(two);
        vm.${stack_vm_name}.push(one);
    }
}