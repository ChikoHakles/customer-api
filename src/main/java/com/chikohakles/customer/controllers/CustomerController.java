package com.chikohakles.customer.controllers;

import com.chikohakles.customer.entities.Customer;
import com.chikohakles.customer.repositories.CustomerRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/customers")
    public ResponseEntity<List<Customer>> findAll(@RequestParam(name = "nama", required = false, defaultValue = "") String nama) {
        try {
            List<Customer> customers;
            if(StringUtils.hasText(nama)) {
                customers = customerRepository.findByNamaContaining(nama);
            }
            else {
                customers = customerRepository.findAll();
            }

            if(customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
