package ru.study.forkjoin.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Offer {
    private final Long id;
    private final Long merchantId;

    public Offer(Long id) {
        this.id = id;
        this.merchantId = id;
    }
}
