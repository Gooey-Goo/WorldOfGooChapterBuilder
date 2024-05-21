package com.gooeygoo.chapterpacker.registries;

import java.util.HashSet;
import java.util.Set;

public abstract class Registry {
    private Set<String> items;

    public Registry() {
        items = new HashSet<String>();
    }

    public void add(String item) {
        items.add(item);
    }

    public abstract void register();

    @Override
    public String toString() {
        return items.toString();
    }
}
