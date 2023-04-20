package com.pichincha.bank.handleexception;

import com.pichincha.bank.model.exception.BusinessException;
import com.pichincha.bank.model.exception.TechnicalException;
import com.pichincha.bank.model.exception.message.TechnicalErrorMessage;
import com.pichincha.bank.model.exception.response.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

import java.util.Collections;
import java.util.Map;

@Component
@Order(-2)
@Log4j2
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {
    private static final String ERROR = "error";

    public GlobalErrorWebExceptionHandler(DefaultErrorAttributes errorAttributes, ApplicationContext applicationContext,
                                          ServerCodecConfigurer serverCodecConfigurer) {
        super(errorAttributes, new WebProperties().getResources(), applicationContext);
        super.setMessageWriters(serverCodecConfigurer.getWriters());
        super.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(final ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    /**
     * Renderiza todas las exceptions de la aplicacion
     *
     * @param request
     * @return Mono<ServerResponse>
     */
    private Mono<ServerResponse> renderErrorResponse(final ServerRequest request) {
        return Mono.just(request)
                .map(this::getError)
                .map(throwable -> throwable)
                .flatMap(Mono::error)
                .onErrorResume(TechnicalException.class, this::buildErrorResponse)
                .onErrorResume(BusinessException.class, this::buildErrorResponse)
                .onErrorResume(this::buildErrorResponse)
                .cast(Tuple2.class)
                .transform(this::castTo)
                .map(errorStatus -> Tuples.of(errorStatus.getT1(), errorStatus.getT2(), request.path()))
                .flatMap(buildRes -> buildResponse(buildRes, request))
                .doAfterTerminate(() -> log.error(getError(request)));
    }

    private Mono<Tuple2<ErrorResponse, HttpStatus>> castTo(Mono<? extends Tuple2> errorStatus) {
        return errorStatus
                .map(Tuple2::getT1)
                .cast(ErrorResponse.class)
                .zipWith(errorStatus
                        .map(Tuple2::getT2)
                        .cast(HttpStatus.class));
    }

    /***
     * Construye el response de las exceptions del tipo TechnicalException
     *
     * @param technicalException
     * @return Mono<Tuple2<ErrorResponse, HttpStatus>>
     */
    private Mono<Tuple2<ErrorResponse, HttpStatus>> buildErrorResponse(TechnicalException technicalException) {
        return Mono.just(ErrorResponse.builder()
                        .reason(technicalException.getTechnicalErrorMessage().getMessage())
                        .code(technicalException.getTechnicalErrorMessage().getCode())
                        .message(technicalException.getTechnicalErrorMessage().getMessage())
                        .build())
                .zipWith(Mono.just(HttpStatus.BAD_REQUEST));
    }

    /**
     * Construye el response de las exceptions del tipo BusinessException
     *
     * @param businessException
     * @return
     */
    private Mono<Tuple2<ErrorResponse, HttpStatus>> buildErrorResponse(BusinessException businessException) {
        return Mono.just(businessException)
                .map(BusinessException::getBusinessErrorMessage)
                .map(errorMsg -> Tuples.of(getErrorResponse(businessException), HttpStatus.BAD_REQUEST));
    }

    private ErrorResponse getErrorResponse(BusinessException businessException) {
        return ErrorResponse.builder()
                .reason(businessException.getBusinessErrorMessage().getMessage())
                .code(businessException.getBusinessErrorMessage().getCode())
                .message(businessException.getBusinessErrorMessage().getMessage())
                .build();
    }

    /**
     * Construye el response de las exceptions no controladas
     *
     * @param throwable
     * @return
     */
    private Mono<Tuple2<ErrorResponse, HttpStatus>> buildErrorResponse(Throwable throwable) {
        return Mono.justOrEmpty(throwable)
                .defaultIfEmpty(throwable)
                .flatMap(Mono::error)
                .onErrorResume(ResponseStatusException.class, exception -> Mono.just(ErrorResponse.builder()
                        .reason(TechnicalErrorMessage.SERVICE_NOT_FOUND.getMessage())
                        .code(TechnicalErrorMessage.SERVICE_NOT_FOUND.getCode())
                        .message(TechnicalErrorMessage.SERVICE_NOT_FOUND.getMessage())
                        .build()))
                .onErrorResume(Throwable.class, exception -> Mono.just(ErrorResponse.builder()
                        .reason(TechnicalErrorMessage.UNEXPECTED_EXCEPTION.getMessage())
                        .code(TechnicalErrorMessage.UNEXPECTED_EXCEPTION.getCode())
                        .message(TechnicalErrorMessage.UNEXPECTED_EXCEPTION.getMessage())
                        .build()))
                .ofType(ErrorResponse.class)
                .zipWith(Mono.just(HttpStatus.BAD_GATEWAY));
    }

    private Mono<ServerResponse> buildResponse(Tuple3<ErrorResponse, HttpStatus, String> errorStatus,
                                               final ServerRequest request) {
        return ServerResponse.status(errorStatus.getT2())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(getErrors(errorStatus));
    }

    private Map<String, ErrorResponse> getErrors(Tuple3<ErrorResponse, HttpStatus, String> errorStatus) {
        return Collections.singletonMap(ERROR, errorStatus.getT1().toBuilder()
                .domain(errorStatus.getT3()).build());
    }
}