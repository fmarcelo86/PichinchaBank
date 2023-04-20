package com.pichincha.bank.api.costumer.validation;

import com.pichincha.bank.api.costumer.dto.CustomerRequest;
import com.pichincha.bank.model.exception.BusinessException;
import com.pichincha.bank.model.exception.message.BusinessErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.validation.Validator;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CustomerValidator {
    private final Validator validator;

    public Mono<CustomerRequest> validateBody(CustomerRequest customerRequest) {
        return Mono.just(customerRequest)
                .map(validator::validate)
                .map(constraintViolations -> constraintViolations)
                .map(Set::isEmpty)
                .filter(isValid -> isValid)
                .switchIfEmpty(Mono.error(new BusinessException(BusinessErrorMessage.BAD_REQUEST_BODY)))
                .thenReturn(customerRequest);
    }
}
