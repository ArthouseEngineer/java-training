package ru.study.forkjoin.services;

import ru.study.forkjoin.actions.ActiveOffersWarmUpAction;

import java.util.concurrent.ForkJoinPool;

public class CacheWarmUpService {

    public static void main(String[] args) {
        var forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new ActiveOffersWarmUpAction());
        forkJoinPool.shutdown();
    }
}
