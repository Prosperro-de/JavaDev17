package org.example.annotationdemo;

import java.lang.reflect.Field;
import java.util.Arrays;

public class CustomerService {
    public void printCustomerDetails(Customer customer) {
        try {
            processTrimAnnotation(customer);
            System.out.println("customer = " + customer);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    private void processTrimAnnotation(Customer customer) throws IllegalAccessException {
        Field[] declaredFields = customer.getClass().getDeclaredFields();
        System.out.println(Arrays.toString(declaredFields));
        for (int i = 0; i < declaredFields.length; i++) {
            if (declaredFields[i].isAnnotationPresent(Trim.class)) {
                declaredFields[i].setAccessible(true);
                String value = (String) declaredFields[i].get(customer);
                declaredFields[i].set(customer, value.trim());
                declaredFields[i].setAccessible(false);
            }
        }
    }


}
