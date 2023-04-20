package com.pichincha.bank.model.customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends Person {
    private Integer ID;
    private String password;
    private Boolean Status;
}
