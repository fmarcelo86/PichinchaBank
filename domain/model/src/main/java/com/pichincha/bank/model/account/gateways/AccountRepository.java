package com.pichincha.bank.model.account.gateways;

import com.pichincha.bank.model.account.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepository {
    Flux<Account> getAll();
    Mono<Account> save(Account account);
    Mono<Account> update(Account account);
    Mono<Account> findByAccountNumber(String accountNumber);
    Mono<Void> delete(Integer ID);
}
