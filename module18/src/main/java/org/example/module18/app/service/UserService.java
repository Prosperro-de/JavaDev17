package org.example.module18.app.service;

import lombok.RequiredArgsConstructor;
import org.example.module18.app.model.Customer;
import org.example.module18.app.model.CustomerDetails;
import org.example.module18.app.model.Role;
import org.example.module18.app.model.User;
import org.example.module18.app.model.dto.request.AuthRequest;
import org.example.module18.app.repository.RoleRepository;
import org.example.module18.app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String createUser(AuthRequest request) {
        if (userRepository.existsByUsername(request.getEmail())) {
            return "User with username: %s already exists".formatted(request.getEmail());
        }
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setPostCode(request.getPostCode());


        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setCustomer(customer);
        if (request.getDateOfBirth() != null) {
            customerDetails.setDateOfBirth(LocalDate.parse(request.getDateOfBirth()));
        }

        customer.setCustomerDetails(customerDetails);

        User user = new User();
        user.setUsername(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(Boolean.TRUE);

        roleRepository.findByName("ROLE_USER").ifPresent(user::addRole);

        user.setCustomer(customer);

        userRepository.save(user);
        return "User created";
    }

    public User findByName(String name) {
        return userRepository.findByUsername(name).orElseThrow();
    }
}
