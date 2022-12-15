package xyz.davidpineiro.jpush.vm.instruction.integer;

import xyz.davidpineiro.jpush.vm.instruction.annotationHelper.GenerateStackIntructions;

@GenerateStackIntructions(numeric = true)
public class IntInstructionConfig {

    public static final String stack_name = "Int";
    public static final String stack_java_type = "Integer";
    public static final String stack_vm_name = "intStack";

}
