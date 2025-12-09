package com.satwik.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satwik.account.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Optional<Customer> findByMobileNumber(String mobileNumber);
}
