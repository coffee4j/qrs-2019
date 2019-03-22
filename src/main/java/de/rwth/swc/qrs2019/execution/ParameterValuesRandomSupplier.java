package de.rwth.swc.qrs2019.execution;

import org.apache.commons.collections4.iterators.PermutationIterator;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ParameterValuesRandomSupplier<T extends Enum<T>> {
    private final List<List<T>> permutations;
    private final Class<T> clazz;

    public ParameterValuesRandomSupplier(Class<T> clazz) {
        T[] values = null;

        try {
            final Method method = clazz.getDeclaredMethod("values");

            @SuppressWarnings("unchecked")
            T[] result = (T[]) method.invoke(null);

            values = result;

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        this.permutations = values != null ? listPermutations(values) : Collections.emptyList();
        this.clazz = clazz;
    }

    public T[] get() {
        final int randomIndex = ThreadLocalRandom.current().nextInt(0, permutations.size());
        final List<T> list = permutations.get(randomIndex);

        @SuppressWarnings("unchecked")
        final T[] permutation = list.toArray((T[]) Array.newInstance(clazz, 0));

        return permutation;
    }

    private List<List<T>> listPermutations(T[] array) {
        return listPermutations(Arrays.asList(array));
    }

    private List<List<T>> listPermutations(Collection<T> collection) {
        final PermutationIterator<T> iterator = new PermutationIterator<>(collection);

        return toStream(iterator).collect(Collectors.toList());
    }

    private Stream<List<T>> toStream(Iterator<List<T>> iterator) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
    }
}
