package org.spring.demo.module15.app.util;

import lombok.experimental.UtilityClass;
import org.spring.demo.module15.app.dao.model.Customer;
import org.spring.demo.module15.app.dao.model.CustomerDetails;
import org.spring.demo.module15.app.dao.model.dto.CustomerResponse;

@UtilityClass
public class CustomerCustomMapper {
    public CustomerResponse toDto(Customer entity) {
        CustomerResponse response = new CustomerResponse();
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setEmail(entity.getEmail());
        response.setPhoneNumber(entity.getPhoneNumber());
        response.setPostCode(entity.getPostCode());
        CustomerDetails customerDetails = entity.getCustomerDetails();
        if (customerDetails != null) {
            response.setLoyaltyPoints(customerDetails.getLoyaltyPoints());
            response.setDateOfBirth(customerDetails.getDateOfBirth());
        }
        return response;
    }
}
