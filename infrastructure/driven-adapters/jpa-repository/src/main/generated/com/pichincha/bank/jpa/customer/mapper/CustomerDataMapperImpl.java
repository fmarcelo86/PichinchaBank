package com.pichincha.bank.jpa.customer.mapper;

import com.pichincha.bank.jpa.customer.data.CustomerData;
import com.pichincha.bank.model.customer.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-20T08:19:16-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.8 (Oracle Corporation)"
)
@Component
public class CustomerDataMapperImpl implements CustomerDataMapper {

    @Override
    public Customer toEntity(CustomerData customerData, Customer customer) {
        if ( customerData == null ) {
            return customer;
        }

        customer.setName( customerData.getName() );
        customer.setGender( customerData.getGender() );
        customer.setAge( customerData.getAge() );
        customer.setIdentification( customerData.getIdentification() );
        customer.setAddress( customerData.getAddress() );
        customer.setPhone( customerData.getPhone() );
        customer.setID( customerData.getID() );
        customer.setPassword( customerData.getPassword() );
        customer.setStatus( customerData.getStatus() );

        return customer;
    }

    @Override
    public CustomerData toData(Customer customer, CustomerData customerData) {
        if ( customer == null ) {
            return customerData;
        }

        customerData.setName( customer.getName() );
        customerData.setGender( customer.getGender() );
        customerData.setAge( customer.getAge() );
        customerData.setIdentification( customer.getIdentification() );
        customerData.setAddress( customer.getAddress() );
        customerData.setPhone( customer.getPhone() );
        customerData.setID( customer.getID() );
        customerData.setPassword( customer.getPassword() );
        customerData.setStatus( customer.getStatus() );

        return customerData;
    }
}
