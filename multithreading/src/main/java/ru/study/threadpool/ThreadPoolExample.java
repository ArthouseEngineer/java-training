package ru.study.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Slf4j
public class ThreadPoolExample {

    public static void main(String[] args) throws InterruptedException {
        var start = System.currentTimeMillis();

        var cachedPool = Executors.newCachedThreadPool();

        OneMicroSecondTask task = new OneMicroSecondTask();

        List<Callable<String>> tasks = IntStream.rangeClosed(1, 1_000_000)
                .mapToObj(i -> task)
                .collect(toList());

        cachedPool.invokeAll(tasks);

        long timeSpent = System.currentTimeMillis() - start;
        log.info("Time spent: {} ms ", timeSpent);

        cachedPool.shutdownNow();
    }

    static class OneMicroSecondTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            var oneMicroSecond = 1_000;
            var startedAt = System.nanoTime();
            while (System.nanoTime() - startedAt <= oneMicroSecond) ;

            return "DONE";
        }
    }
}
