package com.pichincha.bank.api.costumer.mapper;

import com.pichincha.bank.api.costumer.dto.CustomerRequest;
import com.pichincha.bank.model.customer.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-19T22:08:22-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.8 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toEntity(CustomerRequest request, Customer customer) {
        if ( request == null ) {
            return customer;
        }

        customer.setName( request.getName() );
        customer.setGender( request.getGender() );
        customer.setAge( request.getAge() );
        customer.setIdentification( request.getIdentification() );
        customer.setAddress( request.getAddress() );
        customer.setPhone( request.getPhone() );
        customer.setID( request.getID() );
        customer.setPassword( request.getPassword() );
        customer.setStatus( request.getStatus() );

        return customer;
    }
}
