package com.pichincha.bank.jpa.account.data;

import com.pichincha.bank.jpa.customer.data.CustomerData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Account")
public class AccountData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;
    @Column(unique = true)
    private String accountNumber;
    private String type;
    private Double initialBalance;
    private String status;
    private Integer clientId;
    @OneToOne
    private CustomerData customerData;
}
