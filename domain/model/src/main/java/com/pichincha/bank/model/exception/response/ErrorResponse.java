package com.pichincha.bank.model.exception.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ErrorResponse {
    private String reason;
    private String domain;
    private String code;
    private String message;
}