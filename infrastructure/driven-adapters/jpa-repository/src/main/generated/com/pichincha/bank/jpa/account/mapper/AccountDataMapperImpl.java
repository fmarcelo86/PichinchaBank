package com.pichincha.bank.jpa.account.mapper;

import com.pichincha.bank.jpa.account.data.AccountData;
import com.pichincha.bank.jpa.customer.data.CustomerData;
import com.pichincha.bank.model.account.Account;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-20T08:19:16-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.8 (Oracle Corporation)"
)
@Component
public class AccountDataMapperImpl implements AccountDataMapper {

    @Override
    public Account toEntity(AccountData accountData) {
        if ( accountData == null ) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        account.client( accountDataCustomerDataName( accountData ) );
        account.ID( accountData.getID() );
        account.accountNumber( accountData.getAccountNumber() );
        account.type( accountData.getType() );
        account.initialBalance( accountData.getInitialBalance() );
        account.status( accountData.getStatus() );
        account.clientId( accountData.getClientId() );

        return account.build();
    }

    @Override
    public AccountData toData(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountData.AccountDataBuilder accountData = AccountData.builder();

        accountData.ID( account.getID() );
        accountData.accountNumber( account.getAccountNumber() );
        accountData.type( account.getType() );
        accountData.initialBalance( account.getInitialBalance() );
        accountData.status( account.getStatus() );
        accountData.clientId( account.getClientId() );

        return accountData.build();
    }

    private String accountDataCustomerDataName(AccountData accountData) {
        if ( accountData == null ) {
            return null;
        }
        CustomerData customerData = accountData.getCustomerData();
        if ( customerData == null ) {
            return null;
        }
        String name = customerData.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
