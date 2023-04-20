package com.pichincha.bank.api.movement;

import com.pichincha.bank.api.movement.dto.MovementRequest;
import com.pichincha.bank.api.movement.mapper.MovementMapper;
import com.pichincha.bank.api.movement.validation.MovementValidator;
import com.pichincha.bank.model.movement.Movement;
import com.pichincha.bank.model.movement.Report;
import com.pichincha.bank.usecase.movement.MovementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
@RequiredArgsConstructor
public class MovementHandler {
    private static final String ID_PATH_VARIABLE = "id";
    private final MovementUseCase useCase;
    private final MovementMapper mapper;
    private final MovementValidator movementValidator;

    public Mono<ServerResponse> getAll() {
        return ServerResponse.ok()
                .body(useCase.getAll(), Movement.class);
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(MovementRequest.class)
                .flatMap(movementValidator::validateBody)
                .map(mapper::toEntity)
                .flatMap(useCase::save)
                .zipWith(Mono.just(HttpStatus.CREATED))
                .transform(this::buildSuccessfulResponse);
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(MovementRequest.class)
                .flatMap(movementValidator::validateBody)
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

    public Mono<ServerResponse> report(ServerRequest serverRequest) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return Mono.just(serverRequest)
                .map(ServerRequest::queryParams)
                .map(params -> {
                    try {
                        return Report.builder()
                                .clientId(Integer.valueOf(params.getFirst("clientId")))
                                .startDate(df.parse(params.getFirst("startDate")))
                                .endDate(df.parse(params.getFirst("endDate")))
                                .build();
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .flatMap(report -> ServerResponse.ok()
                        .body(useCase.report(report), Movement.class));
    }

    private Mono<ServerResponse> buildSuccessfulResponse(Mono<Tuple2<Movement, HttpStatus>> responseStatus) {
        return responseStatus.flatMap(tuple -> ServerResponse
                .status(tuple.getT2())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(tuple.getT1()));
    }
}
