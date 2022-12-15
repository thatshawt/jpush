package ${stack_instruction_path};

import xyz.davidpineiro.jpush.vm.*;
import xyz.davidpineiro.jpush.vm.instruction.*;
import ${stack_instruction_path}.*;

public abstract class Binary${stack_name}Operation extends BinaryInstructionOperation<${stack_java_type}> {
    @Override
    protected PushStack<${stack_java_type}> getStack(PushVM vm) {
        return vm.${stack_vm_name};
    }
}
