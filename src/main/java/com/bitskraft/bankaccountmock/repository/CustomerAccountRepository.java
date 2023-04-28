package com.bitskraft.bankaccountmock.repository;

import com.bitskraft.bankaccountmock.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, String> {

    @Query(value = "SELECT * FROM customer_account WHERE customer_id = ?1", nativeQuery = true)
    List<CustomerAccount> findByCustomerId(String customerId);
    @Query(value = "SELECT * FROM customer_account WHERE customer_id = ?1 and account_type=?2", nativeQuery = true)
    Optional<CustomerAccount> findByCustomerIdAccountType(String customerId,String accountType);
    @Query(value = "SELECT * FROM customer_account WHERE account_number = ?1", nativeQuery = true)
    Optional<CustomerAccount> findByAccountNumber(String accountNumber);
    @Query(value = "SELECT * FROM customer_account WHERE account_number = ?1", nativeQuery = true)
    Optional<CustomerAccount> findByAccNum(String accountNumber);
}
