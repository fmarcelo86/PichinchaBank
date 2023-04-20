package com.pichincha.bank.api.account.mapper;

import com.pichincha.bank.api.account.dto.AccountRequest;
import com.pichincha.bank.model.account.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    Account toEntity(AccountRequest request);

}