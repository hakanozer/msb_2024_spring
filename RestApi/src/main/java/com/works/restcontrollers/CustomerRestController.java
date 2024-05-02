package com.works.restcontrollers;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("customer")
public class CustomerRestController {

    final CustomerService customerService;

    @PostMapping("register")
    public ResponseEntity register(@Valid @RequestBody Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }
        return customerService.register(customer);
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestParam String username, @RequestParam String password) {
        return customerService.login(username, password);
    }

}
