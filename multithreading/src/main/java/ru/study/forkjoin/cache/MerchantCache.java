package ru.study.forkjoin.cache;

import lombok.extern.slf4j.Slf4j;
import ru.study.forkjoin.entity.Merchant;
import ru.study.forkjoin.repository.MerchantRepository;

@Slf4j
public class MerchantCache implements Cache {

    private final MerchantRepository merchantRepository = new MerchantRepository();

    public Merchant getMerchant(Long id) {
        var merchant = merchantRepository.getMerchant(id);
        log.info("Warm up Merchant: {} ", merchant);
        return merchant;
    }
}
