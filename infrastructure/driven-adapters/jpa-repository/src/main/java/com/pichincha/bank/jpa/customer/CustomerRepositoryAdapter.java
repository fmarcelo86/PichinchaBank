package com.pichincha.bank.jpa.customer;

import com.pichincha.bank.jpa.customer.data.CustomerData;
import com.pichincha.bank.jpa.customer.mapper.CustomerDataMapper;
import com.pichincha.bank.model.customer.Customer;
import com.pichincha.bank.model.customer.gateways.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {
    private final CustomerDataRepository repository;
    private final CustomerDataMapper mapper;

    @Override
    public Flux<Customer> getAll() {
        return Flux.fromIterable(repository.findAll())
                .map(customerData -> mapper.toEntity(customerData, Customer.builder().build()));
    }

    @Override
    public Mono<Customer> findById(Integer ID) {
        return Mono.justOrEmpty(repository.findById(ID))
                .map(customerData -> mapper.toEntity(customerData, Customer.builder().build()));
    }

    @Override
    public Mono<Customer> save(Customer entity) {
        entity.toBuilder().ID(null);
        return Mono.just(entity)
                .map(customer -> mapper.toData(customer, CustomerData.builder().build()))
                .map(repository::save)
                .map(customerData -> mapper.toEntity(customerData, Customer.builder().build()));
    }

    @Override
    public Mono<Customer> update(Customer entity) {
        return Mono.just(entity)
                .map(customer -> mapper.toData(customer, CustomerData.builder().build()))
                .map(repository::save)
                .map(customerData -> mapper.toEntity(customerData, Customer.builder().build()));
    }

    @Override
    public Mono<Void> delete(Integer ID) {
        return Mono.just(ID)
                .doOnNext(repository::deleteById)
                .onErrorMap(throwable -> throwable)
                .then();
    }
}
