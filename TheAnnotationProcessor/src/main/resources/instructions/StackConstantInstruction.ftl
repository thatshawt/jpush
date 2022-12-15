package ${stack_instruction_path}.stack;

import xyz.davidpineiro.jpush.vm.*;
import xyz.davidpineiro.jpush.vm.instruction.*;
import ${stack_instruction_path}.*;

public class ${stack_name}ConstantInstruction implements Instruction {

   ${stack_java_type} val;

    public ${stack_name}ConstantInstruction(${stack_java_type} val) {
        this.val = val;
    }

    @Override
    public void exec(PushVM vm) {
        vm.${stack_vm_name}.push(val);
    }
}