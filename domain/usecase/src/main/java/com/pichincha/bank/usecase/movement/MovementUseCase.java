package com.pichincha.bank.usecase.movement;

import com.pichincha.bank.model.account.Account;
import com.pichincha.bank.model.account.gateways.AccountRepository;
import com.pichincha.bank.model.exception.BusinessException;
import com.pichincha.bank.model.exception.message.BusinessErrorMessage;
import com.pichincha.bank.model.movement.Movement;
import com.pichincha.bank.model.movement.Report;
import com.pichincha.bank.model.movement.gateways.MovementRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
public class MovementUseCase {
    private final MovementRepository repository;
    private final AccountRepository accountRepository;

    public Flux<Movement> getAll() {
        return repository.getAll();
    }

    public Mono<Movement> save(Movement movement) {
        AtomicReference<Account> accountRef = new AtomicReference<>();
        return accountRepository.findByAccountNumber(movement.getAccountNumber())
                .switchIfEmpty(Mono.error(new BusinessException(BusinessErrorMessage.ACCOUNT_NOT_FOUND)))
                .filter(account -> account.getInitialBalance() >= movement.getAmount())
                .switchIfEmpty(Mono.error(new BusinessException(BusinessErrorMessage.BALANCE_NOT_AVAILABLE)))
                .doOnNext(accountRef::set)
                .map(account -> transaction(movement, account))
                .flatMap(mov -> repository.save(mov))
                .flatMap(mov -> accountRepository.update(accountRef.get().toBuilder()
                        .initialBalance(mov.getAvailableBalance())
                        .build()))
                .thenReturn(movement);
    }

    private Movement transaction(Movement movement, Account account) {
        return movement.toBuilder()
                .initialBalance(account.getInitialBalance())
                .availableBalance(account.getInitialBalance() - movement.getAmount())
                .clientId(account.getClientId())
                .client(account.getClient())
                .date(Calendar.getInstance().getTime())
                .build();
    }

    public Mono<Movement> update(Movement movement) {
        return repository.update(movement);
    }

    public Mono<Void> delete(Integer ID) {
        return repository.delete(ID);
    }

    public Flux<Movement> report(Report report) {
        return repository.report(report);
    }
}
