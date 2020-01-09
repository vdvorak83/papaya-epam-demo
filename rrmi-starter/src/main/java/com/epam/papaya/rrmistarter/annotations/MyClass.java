package com.epam.papaya.rrmistarter.annotations;

import java.util.function.Supplier;

/**
 * @author Evgeny Borisov
 */
public class MyClass implements Supplier<String> {
    @Override
    public String get() {
        return "OK";
    }
}
