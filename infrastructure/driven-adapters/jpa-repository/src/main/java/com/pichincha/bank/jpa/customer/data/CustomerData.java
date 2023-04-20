package com.pichincha.bank.jpa.customer.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Customer")
public class CustomerData extends PersonData {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID;
    private String password;
    private Boolean Status;
}
