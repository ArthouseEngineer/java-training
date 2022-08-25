package ru.study.forkjoin.node;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class NodeImpl implements Node {

    List<Node> nodes;

    long value;

    public NodeImpl(int depth) {
        nodes = IntStream
                .rangeClosed(1, depth - 1)
                .boxed()
                .map(NodeImpl::new)
                .collect(toList());
        value = depth;
    }

    @Override
    public Collection<Node> getChildren() {
        return nodes;
    }

    @Override
    public long getValue() {
        return value;
    }
}
