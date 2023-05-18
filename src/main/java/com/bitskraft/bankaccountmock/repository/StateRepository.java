package com.bitskraft.bankaccountmock.repository;

import com.bitskraft.bankaccountmock.entity.States;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<States,String> {
}
