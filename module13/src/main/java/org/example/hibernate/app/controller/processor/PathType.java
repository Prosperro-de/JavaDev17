package org.example.hibernate.app.controller.processor;

public enum PathType {
    HOME_PAGE("/"),
    CREATE_CUSTOMER_FORM("/createCustomerForm"),
    FIND_BY_ID_FORM("/findCustomerByIdForm"),
    FIND_BY_EMAIL_FORM("/findCustomerByEmailForm"),
    DELETE_CUSTOMER_FORM("/deleteCustomerByIdForm"),
    CREATE_CUSTOMER("/createCustomer"),
    FIND_BY_ID("/findById"),
    FIND_BY_EMAIL("/findByEmail"),
    FIND_ALL("/findAllCustomers"),
    DELETE_BY_ID("/deleteById");

    private String path;

    PathType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public static PathType pathOf(String value) {
        for(PathType v : values()) {
            if (v.getPath().equalsIgnoreCase(value)) return v;
        }
        throw new IllegalArgumentException();
    }
}
