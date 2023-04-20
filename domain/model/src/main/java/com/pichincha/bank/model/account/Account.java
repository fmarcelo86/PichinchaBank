package com.pichincha.bank.model.account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Integer ID;
    private String accountNumber;
    private String type;
    private Double initialBalance;
    private String status;
    private Integer clientId;
    private String client;
}
