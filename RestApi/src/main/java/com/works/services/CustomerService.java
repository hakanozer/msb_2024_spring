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

    public Customer register( Customer customer ) {
        Optional<Customer> optionalCustomer = customerRepository.findByUsernameEqualsIgnoreCase(customer.getUsername());
        if (optionalCustomer.isPresent()) {
            return null;
        }else {
            customerRepository.save(customer);
            return customer;
        }
    }

}
