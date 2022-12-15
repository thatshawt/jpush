package ${stack_instruction_path}.stack;

import xyz.davidpineiro.jpush.vm.*;
import xyz.davidpineiro.jpush.vm.instruction.*;
import ${stack_instruction_path}.*;

public class ${stack_name}EqualInstruction extends Binary${stack_name}Operation {
    @Override
    public ${stack_java_type} getResult(${stack_java_type} a, ${stack_java_type} b) {
        return a == b;
    }
}