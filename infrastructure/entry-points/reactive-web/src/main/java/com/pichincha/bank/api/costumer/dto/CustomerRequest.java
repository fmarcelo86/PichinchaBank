package com.pichincha.bank.api.costumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest extends PersonRequest {
    private Integer ID;
    @NotNull
    private String password;
    @NotNull
    private Boolean Status;
}
