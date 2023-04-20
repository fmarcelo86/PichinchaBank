package com.pichincha.bank.jpa.customer.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "Person")
public class PersonData {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID;
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
}
