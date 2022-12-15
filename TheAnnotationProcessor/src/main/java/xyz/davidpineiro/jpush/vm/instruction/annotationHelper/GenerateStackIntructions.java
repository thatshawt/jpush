package xyz.davidpineiro.jpush.vm.instruction.annotationHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
operators and stack is always generated.
numeric can be set.
 */
@Retention(RetentionPolicy.SOURCE)
public @interface GenerateStackIntructions {

    boolean numeric() default false;

}
