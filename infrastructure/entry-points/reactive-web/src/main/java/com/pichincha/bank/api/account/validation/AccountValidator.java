package com.pichincha.bank.api.account.validation;

import com.pichincha.bank.api.account.dto.AccountRequest;
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
public class AccountValidator {
    private final Validator validator;

    public Mono<AccountRequest> validateBody(AccountRequest accountRequest) {
        return Mono.just(accountRequest)
                .map(validator::validate)
                .map(Set::isEmpty)
                .filter(isValid -> isValid)
                .switchIfEmpty(Mono.error(new BusinessException(BusinessErrorMessage.BAD_REQUEST_BODY)))
                .thenReturn(accountRequest);
    }
}
