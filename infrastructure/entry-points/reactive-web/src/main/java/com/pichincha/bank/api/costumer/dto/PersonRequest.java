package com.pichincha.bank.api.costumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    private Integer ID;
    @NotNull
    private String name;
    @NotNull
    @Pattern(regexp = "Femenino|Masculino")
    private String gender;
    @NotNull
    private Integer age;
    @NotNull
    private String identification;
    @NotNull
    private String address;
    @NotNull
    private String phone;
}
