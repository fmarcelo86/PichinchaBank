package com.pichincha.bank.jpa.movement;

import com.pichincha.bank.jpa.movement.data.MovementData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Date;
import java.util.List;

public interface MovementDataRepository extends CrudRepository<MovementData, Integer>, QueryByExampleExecutor<MovementData> {
    List<MovementData> findByClientIdAndDateBetween(Integer clientId, Date startDate, Date endDate);
}
