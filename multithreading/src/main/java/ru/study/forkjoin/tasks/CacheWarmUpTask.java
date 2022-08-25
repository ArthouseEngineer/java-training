package ru.study.forkjoin.tasks;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveTask;
import java.util.function.Supplier;

@Slf4j
@AllArgsConstructor
public class CacheWarmUpTask<V> extends RecursiveTask<V> {

    private final String cacheName;
    private final Supplier<V> supplier;

    @Override
    protected V compute() {
        log.info("Start cache WarmUp: {} ", cacheName);
        return supplier.get();
    }
}
