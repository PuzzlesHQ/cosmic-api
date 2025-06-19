package io.github.puzzle.cosmic.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Internal
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Unfinished {
}
