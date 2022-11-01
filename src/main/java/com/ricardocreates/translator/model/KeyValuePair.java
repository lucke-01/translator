package com.ricardocreates.translator.model;

import lombok.Getter;
import lombok.Setter;

/**
 * represents key par value its toString method return value property.
 * It is useful using external apis which shows toString() method.
 * while you can show the value property in that external apis like java fx you may want to use the
 * key value internally.
 *
 * @param <T> - key object type
 * @param <E> - value object type
 * @author ricardo
 */
@Getter
@Setter
public class KeyValuePair<T, E> {
    private final T key;
    private final E value;

    public KeyValuePair(T key, E value) {
        this.key = key;
        this.value = value;
    }

    public static <T, E> KeyValuePair<T, E> of(T key, E value) {
        return new KeyValuePair<>(key, value);
    }

    @Override
    public String toString() {
        return value != null ? value.toString() : null;
    }
}