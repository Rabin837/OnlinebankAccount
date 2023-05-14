package com.bitskraft.bankaccountmock.repository;

import com.bitskraft.bankaccountmock.entity.District;
import com.bitskraft.bankaccountmock.entity.States;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, String> {
    List<District> findAllByStates(States states);
}
