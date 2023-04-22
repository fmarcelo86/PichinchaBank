package com.pichincha.bank.api.movement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class MovementRequest {
    private Integer ID;
    @NotNull
    @Positive
    private String accountNumber;
    @NotNull
    @Pattern(regexp = "Ahorro|Corriente")
    private String type;
    @NotNull
    private Double amount;
    @NotNull
    private Boolean status;
    private Date date;
}
