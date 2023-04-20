package com.pichincha.bank.model.movement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Movement {
    private Integer ID;
    private String accountNumber;
    private String type;
    private Double initialBalance;
    private Double amount;
    private Double availableBalance;
    private Boolean status;
    private Integer clientId;
    private String client;
    private Date date;
}
