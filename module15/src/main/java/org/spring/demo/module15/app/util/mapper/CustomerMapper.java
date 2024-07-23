package org.spring.demo.module15.app.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.spring.demo.module15.app.dao.model.Customer;
import org.spring.demo.module15.app.dao.model.dto.CustomerResponse;


@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {


    @Mapping(source = "customerDetails.dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "customerDetails.loyaltyPoints", target = "loyaltyPoints")
    CustomerResponse toCustomerResponse(Customer customer);
}
