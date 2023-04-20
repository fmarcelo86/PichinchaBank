package com.pichincha.bank.api.costumer;

import com.pichincha.bank.api.costumer.dto.CustomerRequest;
import com.pichincha.bank.api.costumer.mapper.CustomerMapper;
import com.pichincha.bank.api.costumer.validation.CustomerValidator;
import com.pichincha.bank.model.customer.Customer;
import com.pichincha.bank.usecase.customer.CustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Component
@RequiredArgsConstructor
public class CustomerHandler {
    private static final String ID_PATH_VARIABLE = "id";
    private final CustomerUseCase useCase;
    private final CustomerMapper mapper;
    private final CustomerValidator customerValidator;

    public Mono<ServerResponse> getAll() {
        return ServerResponse.ok()
                .body(useCase.getAll(), Customer.class);
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CustomerRequest.class)
                .flatMap(customerValidator::validateBody)
                .map(customerRequest -> mapper.toEntity(customerRequest, Customer.builder().build()))
                .flatMap(useCase::save)
                .zipWith(Mono.just(HttpStatus.CREATED))
                .transform(this::buildSuccessfulResponse);
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CustomerRequest.class)
                .flatMap(customerValidator::validateBody)
                .map(customerRequest -> mapper.toEntity(customerRequest, Customer.builder().build()))
                .flatMap(useCase::update)
                .zipWith(Mono.just(HttpStatus.OK))
                .transform(this::buildSuccessfulResponse);
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        return Mono.just(serverRequest)
                .map(request -> request.pathVariable(ID_PATH_VARIABLE))
                .map(Integer::valueOf)
                .flatMap(useCase::delete)
                .then(ServerResponse.noContent().build());
    }

    private Mono<ServerResponse> buildSuccessfulResponse(Mono<Tuple2<Customer, HttpStatus>> responseStatus) {
        return responseStatus.flatMap(tuple -> ServerResponse
                .status(tuple.getT2())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(tuple.getT1()));
    }
}
