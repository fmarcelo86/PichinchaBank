package com.pichincha.bank.model.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BusinessErrorMessage {
    TRANSACTION_OK("PBB0000", "Successful transaction"),
    CLIENT_NOT_FOUND("PBB0001", "Client not found"),
    BAD_REQUEST_BODY("PBB0002", "Error in body request"),
    ACCOUNT_NOT_FOUND("PBB0003", "Account number not found"),
    BALANCE_NOT_AVAILABLE("PBB0004", "Saldo no disponible");

    private final String code;
    private final String message;
}