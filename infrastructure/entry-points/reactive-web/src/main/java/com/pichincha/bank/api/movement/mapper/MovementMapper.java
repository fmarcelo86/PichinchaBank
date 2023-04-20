package com.pichincha.bank.api.movement.mapper;

import com.pichincha.bank.api.movement.dto.MovementRequest;
import com.pichincha.bank.model.movement.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovementMapper {
    Movement toEntity(MovementRequest request);
}