package ru.study.forkjoin.node;

import java.util.Collection;

public interface Node {

    Collection<Node> getChildren();

    long getValue();
}
