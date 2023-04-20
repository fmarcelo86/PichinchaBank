package com.pichincha.bank.model.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TechnicalErrorMessage {
    SERVICE_NOT_FOUND("PBT0001", "Service not found"),
    UNEXPECTED_EXCEPTION("PBT0002", "Unexpected error");

    private final String code;
    private final String message;
}