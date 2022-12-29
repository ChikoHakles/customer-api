package com.chikohakles.customer.repositories;

import com.chikohakles.customer.entities.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String>{
    //bikin interface CustomerRepository (bentuknya List berisi Customer)
    public List<Customer> findByNamaContaining(String nama);
}
