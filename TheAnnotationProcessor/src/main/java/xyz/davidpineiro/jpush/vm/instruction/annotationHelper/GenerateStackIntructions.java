package xyz.davidpineiro.jpush.vm.instruction.annotationHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Optional;

/*
operators and stack is always generated.
numeric can be set.
 */
@Retention(RetentionPolicy.SOURCE)
public @interface GenerateStackIntructions {

    enum TemplateVariable{
        STACK_NAME("stack_name"),
        STACK_JAVA_TYPE("stack_java_type"),
        STACK_VM_NAME("stack_vm_name"),
        STACK_INSTRUCTION_PATH("stack_instruction_path"),
        ;

        public final String a;
        public String toString(){
            return this.a;
        }

        TemplateVariable(String sus) {
            this.a = sus;
        }
    }


    String stack_name();
    String stack_java_type();
    String stack_vm_name();
    String stack_instruction_path() default "";
    boolean numeric() default false;

}
