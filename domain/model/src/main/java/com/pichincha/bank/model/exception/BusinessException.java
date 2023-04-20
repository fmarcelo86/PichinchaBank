package com.pichincha.bank.model.exception;

import com.pichincha.bank.model.exception.message.BusinessErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessException extends Exception {
    private final BusinessErrorMessage businessErrorMessage;
}