package com.pichincha.bank.jpa.customer;

import com.pichincha.bank.jpa.customer.data.CustomerData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface CustomerDataRepository extends CrudRepository<CustomerData, Integer>, QueryByExampleExecutor<CustomerData> {
}
