package ${stack_instruction_path}.stack;

import xyz.davidpineiro.jpush.vm.*;
import xyz.davidpineiro.jpush.vm.instruction.*;
import ${stack_instruction_path}.*;

public class ${stack_name}DupInstruction extends Unary${stack_name}Operation {

    public ${stack_name}DupInstruction() {
        this.keepInputs = true;//cus duplicate lollll
    }

    @Override
    public ${stack_java_type} getResult(${stack_java_type} a) {
        return a;
    }
}