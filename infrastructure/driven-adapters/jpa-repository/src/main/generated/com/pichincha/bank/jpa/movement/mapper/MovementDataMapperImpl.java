package com.pichincha.bank.jpa.movement.mapper;

import com.pichincha.bank.jpa.movement.data.MovementData;
import com.pichincha.bank.model.movement.Movement;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-20T08:19:16-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.8 (Oracle Corporation)"
)
@Component
public class MovementDataMapperImpl implements MovementDataMapper {

    @Override
    public Movement toEntity(MovementData movementData) {
        if ( movementData == null ) {
            return null;
        }

        Movement.MovementBuilder movement = Movement.builder();

        movement.ID( movementData.getID() );
        movement.accountNumber( movementData.getAccountNumber() );
        movement.type( movementData.getType() );
        movement.initialBalance( movementData.getInitialBalance() );
        movement.amount( movementData.getAmount() );
        movement.availableBalance( movementData.getAvailableBalance() );
        movement.status( movementData.getStatus() );
        movement.clientId( movementData.getClientId() );
        movement.client( movementData.getClient() );
        movement.date( movementData.getDate() );

        return movement.build();
    }

    @Override
    public MovementData toData(Movement movement) {
        if ( movement == null ) {
            return null;
        }

        MovementData.MovementDataBuilder movementData = MovementData.builder();

        movementData.ID( movement.getID() );
        movementData.accountNumber( movement.getAccountNumber() );
        movementData.type( movement.getType() );
        movementData.initialBalance( movement.getInitialBalance() );
        movementData.amount( movement.getAmount() );
        movementData.availableBalance( movement.getAvailableBalance() );
        movementData.status( movement.getStatus() );
        movementData.clientId( movement.getClientId() );
        movementData.client( movement.getClient() );
        movementData.date( movement.getDate() );

        return movementData.build();
    }
}
