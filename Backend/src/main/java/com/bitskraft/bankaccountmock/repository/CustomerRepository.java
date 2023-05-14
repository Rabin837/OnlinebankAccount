package com.bitskraft.bankaccountmock.repository;

import com.bitskraft.bankaccountmock.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {


    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);
    @Query(value = "SELECT * FROM customer WHERE cif_id = ?1", nativeQuery = true)
    Optional<Customer> findByCifId(String cifId);
}
