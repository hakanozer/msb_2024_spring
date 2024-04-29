package com.works.services;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;

    public boolean login(Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findByUsernameEqualsIgnoreCaseAndPasswordEquals(customer.getUsername(), customer.getPassword());
        if (optionalCustomer.isPresent()) {
            return true;
        }
        return false;
    }

}
