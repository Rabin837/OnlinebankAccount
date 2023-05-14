package com.bitskraft.bankaccountmock.service;

import com.bitskraft.bankaccountmock.dto.StatesDTO;
import com.bitskraft.bankaccountmock.entity.Country;
import com.bitskraft.bankaccountmock.entity.States;

import java.util.List;
import java.util.Optional;

public interface StateService {
    List<States> findAll();

    StatesDTO findById(String id);

    States findStateById(String id);

    void save(List<StatesDTO> statesDTO, Country country);

}
