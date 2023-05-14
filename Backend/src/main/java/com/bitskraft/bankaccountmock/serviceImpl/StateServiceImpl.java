package com.bitskraft.bankaccountmock.serviceImpl;

import com.bitskraft.bankaccountmock.customerexception.EntityNotFound;
import com.bitskraft.bankaccountmock.dto.StatesDTO;
import com.bitskraft.bankaccountmock.entity.Country;
import com.bitskraft.bankaccountmock.entity.States;
import com.bitskraft.bankaccountmock.repository.StateRepository;
import com.bitskraft.bankaccountmock.service.DistrictService;
import com.bitskraft.bankaccountmock.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StateServiceImpl implements StateService {
    StateRepository stateRepository;
    DistrictService districtService;

    @Autowired
    StateServiceImpl(StateRepository stateRepository, DistrictService districtService) {
        this.stateRepository = stateRepository;
        this.districtService = districtService;
    }

    @Override
    public List<States> findAll() {
        return stateRepository.findAll();
    }

    @Override
    public StatesDTO findById(String id) {
        StatesDTO statesDTO = new StatesDTO();
        States states = findStateById(id);
        statesDTO.setName(states.getName());
        return statesDTO;
    }

    @Override
    public States findStateById(String id) {
        States states = stateRepository.findById(id).orElseThrow(()->new EntityNotFound("State not found"));
        states.setName(states.getName());
        return states;
    }

    @Override
    public void save(List<StatesDTO> statesDTO, Country country) {
        for (StatesDTO dto : statesDTO) {
            States states = new States();
            states.setId(UUID.randomUUID().toString());
            states.setName(dto.getName());
            states.setCountry(country);
            states = stateRepository.save(states);
            districtService.save(dto.getDistricts(), states);
        }
    }
}
