package com.pichincha.bank.api.costumer.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper=false)
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
