package com.pichincha.bank.model.customer;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends Person {
    private Integer ID;
    private String password;
    private Boolean Status;
}
