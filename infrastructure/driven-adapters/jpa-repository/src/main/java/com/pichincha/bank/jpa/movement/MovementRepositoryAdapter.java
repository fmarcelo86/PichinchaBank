package com.pichincha.bank.jpa.movement;

import com.pichincha.bank.jpa.movement.mapper.MovementDataMapper;
import com.pichincha.bank.model.movement.Movement;
import com.pichincha.bank.model.movement.Report;
import com.pichincha.bank.model.movement.gateways.MovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MovementRepositoryAdapter implements MovementRepository {
    private final MovementDataRepository repository;
    private final MovementDataMapper mapper;

    @Override
    public Flux<Movement> getAll() {
        return Flux.fromIterable(repository.findAll())
                .map(mapper::toEntity);
    }

    @Override
    public Mono<Movement> save(Movement entity) {
        entity.toBuilder().ID(null);
        return Mono.just(entity)
                .map(mapper::toData)
                .map(repository::save)
                .map(mapper::toEntity);
    }

    @Override
    public Mono<Movement> update(Movement entity) {
        return Mono.just(entity)
                .map(mapper::toData)
                .map(repository::save)
                .map(mapper::toEntity);
    }

    @Override
    public Mono<Void> delete(Integer ID) {
        return Mono.just(ID)
                .doOnNext(repository::deleteById)
                .onErrorMap(throwable -> throwable)
                .then();
    }

    @Override
    public Flux<Movement> report(Report report) {
        return Flux.fromIterable(repository.findByClientIdAndDateBetween(report.getClientId(), report.getStartDate(),
                        report.getEndDate()))
                .map(mapper::toEntity);
    }
}
