package ru.study.forkjoin.services;

import lombok.extern.slf4j.Slf4j;
import ru.study.forkjoin.node.Node;
import ru.study.forkjoin.node.NodeImpl;
import ru.study.forkjoin.node.ValueSumCounter;

import java.util.concurrent.ForkJoinPool;

@Slf4j
public class ForkJoinForNode {
    public static void main(String[] args) {
        Node root = new NodeImpl(25);

        var start = System.currentTimeMillis();

        var result = new ForkJoinPool()
                .invoke(new ValueSumCounter(root));

        var stop = System.currentTimeMillis();

        log.info("Result: {}", result);

        log.info("Time spent: {} ms", stop - start);
    }
}
