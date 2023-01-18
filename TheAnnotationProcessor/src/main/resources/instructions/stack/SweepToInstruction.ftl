package ${stack_instruction_path}.stack;

import xyz.davidpineiro.jpush.vm.*;
import xyz.davidpineiro.jpush.vm.instruction.*;
import ${stack_instruction_path}.*;

public class ${stack_name}SweepToInstruction extends SweepToInstruction<${stack_java_type}> {
    @Override
    public PushStack<${stack_java_type}> from(PushVM vm) {
        return vm.${stack_vm_name};
    }

    @Override
    public PushStack<${stack_java_type}> to(PushVM vm) {
        return vm.${stack_vm_name};
    }
}