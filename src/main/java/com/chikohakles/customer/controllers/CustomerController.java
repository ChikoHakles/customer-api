package com.chikohakles.customer.controllers;

import com.chikohakles.customer.entities.Customer;
import com.chikohakles.customer.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//nambahin /api di url nya
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    //bikin variabel object customer repository (dari file CustomerRepository.java) (bentuknya List)
    private CustomerRepository customerRepository;

    //bikin url "/customers" yg method nya get
    @GetMapping("/customers")
    //function findAll yang bakal return list customer, yg nantinya list itu dirapihin sama responseEntity
    public ResponseEntity<List<Customer>> findAll(@RequestParam(name = "nama", required = false, defaultValue = "") String nama) {
        try {
            //deklarasi list customer
            List<Customer> customers;
            //klo ada parameter nama, yg di execute function yg findByNamaContaining (ada di CustomerRepository.java)
            if(StringUtils.hasText(nama)) {
                customers = customerRepository.findByNamaContaining(nama);
            }
            //klo gada, yg di execute ialah function ini sendiri, yaitu findAll gapake parameter
            else {
                customers = customerRepository.findAll();
            }
            //klo kosong, return httpstatus no content
            if(customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            //klo enggak kosong, return variabel yg udh diisi sebelumnya
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
        //return server error
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //bikin url get /customers/{id}, dimana id nya angka
    @GetMapping("/customers/{id}")
    //yang ini cuma return sebuah customer, bukan list customer
    public ResponseEntity<Customer> findById(@PathVariable("id") Integer id) {
        //cari customer dengan id yg dimaksud di repository
        //pake toString() karena id nya integer tapi input findById harus string
        Optional<Customer> customerData = customerRepository.findById(id.toString());

        //klo ada, tampilin (get() gunanya buat return value klo sebuah var ada value nya)
        if (customerData.isPresent()) {
            return new ResponseEntity<>(customerData.get(),HttpStatus.OK);
        }
        //klo gada, status not found
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
