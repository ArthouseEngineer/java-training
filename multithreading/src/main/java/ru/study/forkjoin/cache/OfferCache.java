package ru.study.forkjoin.cache;

import lombok.extern.slf4j.Slf4j;
import ru.study.forkjoin.entity.Offer;
import ru.study.forkjoin.repository.OfferRepository;

@Slf4j
public class OfferCache implements Cache {

    private final OfferRepository offerRepository = new OfferRepository();

    public Offer get(Long id) {
        var offer = offerRepository.getOffer(id);
        log.info("Warm up offer: {}", offer);
        return offer;
    }
}
