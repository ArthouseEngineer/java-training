package ru.study.forkjoin.actions;

import ru.study.forkjoin.cache.ActiveOffersCache;
import ru.study.forkjoin.cache.MerchantCache;
import ru.study.forkjoin.cache.OfferCache;
import ru.study.forkjoin.entity.Merchant;
import ru.study.forkjoin.entity.Offer;
import ru.study.forkjoin.tasks.CacheWarmUpTask;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class ActiveOffersWarmUpAction extends RecursiveAction {
    private final ActiveOffersCache activeOffersCache = new ActiveOffersCache();
    private final OfferCache offerCache = new OfferCache();
    private final MerchantCache merchantCache = new MerchantCache();

    @Override
    protected void compute() {
        List<ForkJoinTask<Offer>> offerTasks = activeOffersCache.getAll()
                .stream()
                .map(Offer::getId)
                .map(offerId ->
                        new CacheWarmUpTask<>("OffersCache", () -> offerCache.get(offerId))
                )
                .map(RecursiveTask::fork)
                .collect(Collectors.toList());

        List<ForkJoinTask<Merchant>> merchantTasks = offerTasks.stream()
                .map(ForkJoinTask::join)
                .map(offer ->
                        new CacheWarmUpTask<>("MerchantCache", () -> merchantCache.getMerchant(offer.getMerchantId()))
                )
                .map(RecursiveTask::fork)
                .collect(Collectors.toList());

        merchantTasks.forEach(ForkJoinTask::join);
    }
}
