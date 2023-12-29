package com.dream.application.common.util.batch.api;

import java.util.*;

public class ItemBuffer<T> {

    private final Queue<T> buffer;

    public ItemBuffer() {
        this.buffer = new LinkedList<>();
    }

    public void add(T item) {
        buffer.add(item);
    }

    public void addAll(List<T> items) {
        buffer.addAll(items);
    }

    public T poll() {
        return buffer.poll();
    }

    public boolean isEmpty() {
        return buffer.isEmpty();
    }

    public Integer size() {
        return buffer.size();
    }
}
