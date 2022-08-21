package ru.study.forkjoin;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

@AllArgsConstructor
@Slf4j
public class ValueSumCounter extends RecursiveTask<Long> {
    private transient Node node;

    @Override
    protected Long compute() {
        var sum = node.getValue();

        List<ValueSumCounter> subTasks = new LinkedList<>();

        node.getChildren()
                .stream()
                .map(ValueSumCounter::new)
                .forEachOrdered(task -> {
                    task.fork(); // Async starting
                    subTasks.add(task);
                });

        // Выполнил таску и прибавим результат
        for (ValueSumCounter subTask : subTasks) {
            sum += subTask.join();
        }

        return sum;
    }
}
