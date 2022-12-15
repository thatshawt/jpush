package ${stack_instruction_path}.stack;

import xyz.davidpineiro.jpush.vm.*;
import xyz.davidpineiro.jpush.vm.instruction.*;
import ${stack_instruction_path}.*;

public class ${stack_name}ShoveInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.${stack_vm_name}.insertElementAt(vm.${stack_vm_name}.pop(), vm.${stack_vm_name}.size()-1-vm.intStack.pop());
    }
}
