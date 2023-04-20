package com.pichincha.bank.jpa.customer.mapper;

import com.pichincha.bank.jpa.customer.data.CustomerData;
import com.pichincha.bank.model.customer.Customer;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerDataMapper {
    @InheritConfiguration
    Customer toEntity(CustomerData customerData, @MappingTarget Customer customer);

    @InheritConfiguration
    CustomerData toData(Customer customer, @MappingTarget CustomerData customerData);
}