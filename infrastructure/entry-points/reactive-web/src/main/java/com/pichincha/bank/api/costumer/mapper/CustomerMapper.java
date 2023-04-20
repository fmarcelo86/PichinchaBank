package com.pichincha.bank.api.costumer.mapper;

import com.pichincha.bank.api.costumer.dto.CustomerRequest;
import com.pichincha.bank.model.customer.Customer;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    @InheritConfiguration
    Customer toEntity(CustomerRequest request, @MappingTarget Customer customer);
}