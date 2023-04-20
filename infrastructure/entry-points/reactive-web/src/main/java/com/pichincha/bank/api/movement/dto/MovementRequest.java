package com.pichincha.bank.api.movement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
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
    @DecimalMin("1")
    private Double amount;
    @NotNull
    private Boolean status;
    private Date date;
}
