package com.pichincha.bank.usecase.customer;

import com.pichincha.bank.model.customer.Customer;
import com.pichincha.bank.model.customer.gateways.CustomerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CustomerUseCase {
    private final CustomerRepository repository;

    public Flux<Customer> getAll() {
        return repository.getAll();
    }

    public Mono<Customer> save(Customer customer) {
        return repository.save(customer);
    }

    public Mono<Customer> update(Customer customer) {
        return repository.update(customer);
    }

    public Mono<Void> delete(Integer ID) {
       return repository.delete(ID);
    }
}
