package ru.study.forkjoin.cache;

import lombok.extern.slf4j.Slf4j;
import ru.study.forkjoin.entity.Offer;
import ru.study.forkjoin.repository.OfferRepository;

import java.util.List;

@Slf4j
public class ActiveOffersCache implements Cache {
    private final OfferRepository offerRepository = new OfferRepository();

    public List<Offer> getAll() {
        var activeOffers = offerRepository.getOffers();
        log.info("Warm up active offers: {} ", activeOffers);
        return activeOffers;
    }
}
