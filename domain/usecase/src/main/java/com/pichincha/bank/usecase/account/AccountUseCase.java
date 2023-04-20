package com.pichincha.bank.usecase.account;

import com.pichincha.bank.model.account.Account;
import com.pichincha.bank.model.account.gateways.AccountRepository;
import com.pichincha.bank.model.customer.gateways.CustomerRepository;
import com.pichincha.bank.model.exception.BusinessException;
import com.pichincha.bank.model.exception.message.BusinessErrorMessage;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AccountUseCase {
    private final AccountRepository repository;
    private final CustomerRepository customerRepository;

    public Flux<Account> getAll() {
        return repository.getAll();
    }

    public Mono<Account> save(Account account) {
        return customerRepository.findById(account.getClientId())
                .switchIfEmpty(Mono.error(new BusinessException(BusinessErrorMessage.CLIENT_NOT_FOUND)))
                .flatMap(customer -> repository.save(account));
    }

    public Mono<Account> update(Account account) {
        return customerRepository.findById(account.getClientId())
                .switchIfEmpty(Mono.error(new BusinessException(BusinessErrorMessage.CLIENT_NOT_FOUND)))
                .flatMap(customer -> repository.update(account));
    }

    public Mono<Void> delete(Integer ID) {
        return repository.delete(ID);
    }
}
