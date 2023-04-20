package com.pichincha.bank.jpa.account.mapper;

import com.pichincha.bank.jpa.account.data.AccountData;
import com.pichincha.bank.model.account.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountDataMapper {

    @Mapping(target="client", source="customerData.name")
    Account toEntity(AccountData accountData);

    AccountData toData(Account account);
}