package com.bitskraft.bankaccountmock.repository;

import com.bitskraft.bankaccountmock.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,String> {
}
