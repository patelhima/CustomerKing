package com.customerking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customerking.model.Customers;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long>{

}
