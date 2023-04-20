package com.pichincha.bank.model.movement.gateways;

import com.pichincha.bank.model.movement.Movement;
import com.pichincha.bank.model.movement.Report;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovementRepository {
    Flux<Movement> getAll();
    Mono<Movement> save(Movement movement);
    Mono<Movement> update(Movement movement);
    Mono<Void> delete(Integer ID);
    Flux<Movement> report(Report report);
}
