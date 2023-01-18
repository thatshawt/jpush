package ${stack_instruction_path}.stack;

import xyz.davidpineiro.jpush.vm.*;
import xyz.davidpineiro.jpush.vm.instruction.*;
import ${stack_instruction_path}.*;

public class ${stack_name}YoinkInstruction implements Instruction {
    @Override
    public void exec(PushVM vm) {
        vm.${stack_vm_name}.push(vm.${stack_vm_name}.remove(vm.intStack.pop()));
    }
}