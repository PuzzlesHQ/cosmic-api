package io.github.puzzle.cosmic.util;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface QuadFunction<T, U,O,L,R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @param l the third function argument
     * @param o the fourth function argument
     * @return the function result
     */
    R apply(T t, U u,O o,L l);

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> the type of output of the {@code after} function, and of the
     *           composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     */
    default <V> QuadFunction<T,U,O,L,V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u, O o, L l) -> after.apply(apply(t, u, o, l));
    }
}
