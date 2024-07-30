package org.example.module17.app.mapper;


import org.example.module17.app.model.Customer;
import org.example.module17.app.model.dto.CustomerResponse;
import org.example.module17.app.model.dto.request.CustomerUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {
    @Mapping(source = "customerDetails.dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "customerDetails.loyaltyPoints", target = "loyaltyPoints")
    CustomerResponse toCustomerResponse(Customer customer);

    Customer toEntity(CustomerUpdateRequest request);

}
