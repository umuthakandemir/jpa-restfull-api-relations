package com.boomliapps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boomliapps.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
