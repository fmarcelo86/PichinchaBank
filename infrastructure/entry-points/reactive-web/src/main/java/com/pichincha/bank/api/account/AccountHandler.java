package com.pichincha.bank.api.account;

import com.pichincha.bank.api.account.dto.AccountRequest;
import com.pichincha.bank.api.account.mapper.AccountMapper;
import com.pichincha.bank.api.account.validation.AccountValidator;
import com.pichincha.bank.model.account.Account;
import com.pichincha.bank.usecase.account.AccountUseCase;
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
public class AccountHandler {
    private static final String ID_PATH_VARIABLE = "id";
    private final AccountUseCase useCase;
    private final AccountMapper mapper;
    private final AccountValidator accountValidator;

    public Mono<ServerResponse> getAll() {
        return ServerResponse.ok()
                .body(useCase.getAll(), Account.class);
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
       return serverRequest.bodyToMono(AccountRequest.class)
                .flatMap(accountValidator::validateBody)
                .map(mapper::toEntity)
                .flatMap(useCase::save)
                .zipWith(Mono.just(HttpStatus.CREATED))
                .transform(this::buildSuccessfulResponse);
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(AccountRequest.class)
                .flatMap(accountValidator::validateBody)
                .map(mapper::toEntity)
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

    private Mono<ServerResponse> buildSuccessfulResponse(Mono<Tuple2<Account, HttpStatus>> responseStatus) {
        return responseStatus.flatMap(tuple -> ServerResponse
                .status(tuple.getT2())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(tuple.getT1()));
    }
}
