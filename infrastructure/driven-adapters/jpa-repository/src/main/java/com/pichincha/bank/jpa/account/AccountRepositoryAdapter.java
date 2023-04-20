package com.pichincha.bank.jpa.account;

import com.pichincha.bank.jpa.account.mapper.AccountDataMapper;
import com.pichincha.bank.jpa.customer.CustomerDataRepository;
import com.pichincha.bank.jpa.customer.data.CustomerData;
import com.pichincha.bank.model.account.Account;
import com.pichincha.bank.model.account.gateways.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepository {
    private final AccountDataRepository repository;
    private final CustomerDataRepository customerRepository;
    private final AccountDataMapper mapper;

    @Override
    public Flux<Account> getAll() {
        return Flux.fromIterable(repository.findAll())
                .map(mapper::toEntity);
    }

    @Override
    public Mono<Account> save(Account entity) {
        entity.toBuilder().ID(null);
        Optional<CustomerData> customer = customerRepository.findById(entity.getClientId());
        return Mono.just(entity)
                .map(mapper::toData)
                .doOnNext(accountData -> accountData.toBuilder().customerData(customer.get()))
                .map(repository::save)
                .map(accountData -> accountData.toBuilder().customerData(customer.get()).build())
                .map(mapper::toEntity);
    }

    @Override
    public Mono<Account> update(Account entity) {
        AtomicReference<CustomerData> customerRef = new AtomicReference<>();
        return Mono.justOrEmpty(customerRepository.findById(entity.getClientId()))
                .map(customerData -> mapper.toData(entity))
                .map(accountData -> accountData.toBuilder().customerData(customerRef.get()).build())
                .map(repository::save)
                .map(accountData -> accountData.toBuilder().customerData(customerRef.get()).build())
                .map(mapper::toEntity);
    }

    @Override
    public Mono<Account> findByAccountNumber(String accountNumber) {
        return Mono.justOrEmpty(repository.findByAccountNumber(accountNumber))
                .map(accountData -> accountData)
                .map(account -> account.toBuilder()
                        .customerData(customerRepository.findById(account.getClientId()).get()).build())
                .map(mapper::toEntity);
    }

    @Override
    public Mono<Void> delete(Integer ID) {
        return Mono.just(ID)
                .doOnNext(repository::deleteById)
                .onErrorMap(throwable -> throwable)
                .then();
    }
}
