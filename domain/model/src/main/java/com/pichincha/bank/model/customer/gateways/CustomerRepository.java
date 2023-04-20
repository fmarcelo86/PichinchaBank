package com.pichincha.bank.model.customer.gateways;

import com.pichincha.bank.model.customer.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository {
    Flux<Customer> getAll();
    Mono<Customer> findById(Integer ID);
    Mono<Customer> save(Customer customer);
    Mono<Customer> update(Customer customer);
    Mono<Void> delete(Integer ID);
}
