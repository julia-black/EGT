package com.egt.qa.common.gui.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for the Test Case ID.
 * <p>Default value: <i>unknown</i></p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) // on the method level
public @interface TestCaseID {
    String value() default "unknown";
}