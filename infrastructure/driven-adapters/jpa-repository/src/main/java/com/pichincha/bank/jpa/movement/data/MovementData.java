package com.pichincha.bank.jpa.movement.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Movement")
public class MovementData {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
