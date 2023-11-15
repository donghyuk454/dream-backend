package com.dream.application.batch.match.job.dto;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class ItemBuffer<T> {

    private final Queue<T> buffer;

    public ItemBuffer() {
        this.buffer = new PriorityQueue<>();
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
}
