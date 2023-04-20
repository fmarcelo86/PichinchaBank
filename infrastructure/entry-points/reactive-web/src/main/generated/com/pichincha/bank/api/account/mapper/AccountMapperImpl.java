package com.pichincha.bank.api.account.mapper;

import com.pichincha.bank.api.account.dto.AccountRequest;
import com.pichincha.bank.model.account.Account;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-19T21:23:00-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.8 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toEntity(AccountRequest request) {
        if ( request == null ) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        account.ID( request.getID() );
        account.accountNumber( request.getAccountNumber() );
        account.type( request.getType() );
        account.initialBalance( request.getInitialBalance() );
        account.status( request.getStatus() );
        account.clientId( request.getClientId() );

        return account.build();
    }
}
