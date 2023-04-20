package com.pichincha.bank.jpa.movement.mapper;

import com.pichincha.bank.jpa.movement.data.MovementData;
import com.pichincha.bank.model.movement.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovementDataMapper {
    Movement toEntity(MovementData movementData);

    MovementData toData(Movement movement);
}