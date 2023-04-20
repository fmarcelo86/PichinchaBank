package com.pichincha.bank.api.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    private Integer ID;
    @NotNull
    private String accountNumber;
    @NotNull
    @Pattern(regexp = "Ahorro|Corriente")
    private String type;
    @NotNull
    private Double initialBalance;
    @NotNull
    private String status;
    @NotNull
    private Integer clientId;
}
