package com.pichincha.bank.model.movement;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class Report {
    private Integer clientId;
    private Date startDate;
    private Date endDate;
}
