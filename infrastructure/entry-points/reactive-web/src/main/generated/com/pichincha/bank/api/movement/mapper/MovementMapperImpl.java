package com.pichincha.bank.api.movement.mapper;

import com.pichincha.bank.api.movement.dto.MovementRequest;
import com.pichincha.bank.model.movement.Movement;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-19T21:23:00-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.8 (Oracle Corporation)"
)
@Component
public class MovementMapperImpl implements MovementMapper {

    @Override
    public Movement toEntity(MovementRequest request) {
        if ( request == null ) {
            return null;
        }

        Movement.MovementBuilder movement = Movement.builder();

        movement.ID( request.getID() );
        movement.accountNumber( request.getAccountNumber() );
        movement.type( request.getType() );
        movement.amount( request.getAmount() );
        movement.status( request.getStatus() );
        movement.date( request.getDate() );

        return movement.build();
    }
}
