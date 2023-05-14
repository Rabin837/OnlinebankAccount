package com.bitskraft.bankaccountmock.repository;

import com.bitskraft.bankaccountmock.entity.District;
import com.bitskraft.bankaccountmock.entity.Municipality;
import com.bitskraft.bankaccountmock.entity.States;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality,String> {
    List<Municipality> findAllByDistricts(District district);
}
