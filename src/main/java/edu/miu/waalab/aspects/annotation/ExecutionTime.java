package edu.miu.waalab.aspects.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
public @interface ExecutionTime {
}
