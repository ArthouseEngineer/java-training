package ru.study.forkjoin.repository;

import ru.study.forkjoin.entity.Offer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class OfferRepository {

    public Offer getOffer(Long id) {
        return new Offer(id);
    }


    public List<Offer> getOffers() {
        return LongStream.range(1, 2000)
                .boxed()
                .map(Offer::new)
                .collect(Collectors.toList());
    }
}
