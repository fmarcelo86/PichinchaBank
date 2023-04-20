package com.pichincha.bank.api.movement.validation;

import com.pichincha.bank.api.movement.dto.MovementRequest;
import com.pichincha.bank.model.exception.BusinessException;
import com.pichincha.bank.model.exception.message.BusinessErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.validation.Validator;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MovementValidator {
    private final Validator validator;

    public Mono<MovementRequest> validateBody(MovementRequest movementRequest) {
        return Mono.just(movementRequest)
                .map(validator::validate)
                .map(Set::isEmpty)
                .filter(isValid -> isValid)
                .switchIfEmpty(Mono.error(new BusinessException(BusinessErrorMessage.BAD_REQUEST_BODY)))
                .thenReturn(movementRequest);
    }
}
