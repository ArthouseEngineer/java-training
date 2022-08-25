package ru.study.forkjoin.repository;

import ru.study.forkjoin.entity.Merchant;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class MerchantRepository {

    public Merchant getMerchant(Long id) {
        return new Merchant(id);
    }


    public List<Merchant> getMerchants() {
        return LongStream.range(1, 2000)
                .boxed()
                .map(Merchant::new)
                .collect(Collectors.toList());
    }
}
