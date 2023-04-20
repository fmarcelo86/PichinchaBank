package com.pichincha.bank.jpa.account;

import com.pichincha.bank.jpa.account.data.AccountData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface AccountDataRepository extends CrudRepository<AccountData, Integer>, QueryByExampleExecutor<AccountData> {
    AccountData findByAccountNumber(String accountNumber);
}
