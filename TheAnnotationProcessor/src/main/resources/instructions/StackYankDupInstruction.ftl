package ${stack_instruction_path}.stack;

import xyz.davidpineiro.jpush.vm.*;
import xyz.davidpineiro.jpush.vm.instruction.*;
import ${stack_instruction_path}.*;

public class ${stack_name}YankDupInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.${stack_vm_name}.push(vm.${stack_vm_name}.get(vm.${stack_vm_name}.size()-1-vm.intStack.pop()));
    }
}