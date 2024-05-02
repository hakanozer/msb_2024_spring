package com.works.services;

import com.works.entities.Customer;
import com.works.models.Result;
import com.works.repositories.CustomerRepository;
import com.works.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;
    final TinkEncDec tinkEncDec;

    public ResponseEntity register(Customer customer ) {
        Optional<Customer> optionalCustomer = customerRepository.findByUsernameEqualsIgnoreCase(customer.getUsername());
        if (optionalCustomer.isPresent()) {
            return Util.fail("Username in use!", customer.getUsername(), HttpStatus.BAD_REQUEST);
        }else {
            String newPass = tinkEncDec.encrypt(customer.getPassword());
            customer.setPassword(newPass);
            customerRepository.save(customer);
            return Util.success(customer);
        }
    }

    public ResponseEntity login(String username, String password) {
        Optional<Customer> optionalCustomer = customerRepository.findByUsernameEqualsIgnoreCase(username);
        if (optionalCustomer.isPresent()) {
            Customer c = optionalCustomer.get();
            String dbPass = tinkEncDec.decrypt(c.getPassword());
            if (dbPass.equals(password)) {
                c.setPassword("*****");
                return Util.success(c);
            }
        }
        return Util.fail("Username or Password fail", null, HttpStatus.BAD_REQUEST);
    }

}
