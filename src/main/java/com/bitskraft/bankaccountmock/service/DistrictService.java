package com.bitskraft.bankaccountmock.service;

import com.bitskraft.bankaccountmock.dto.DistrictDTO;
import com.bitskraft.bankaccountmock.entity.District;
import com.bitskraft.bankaccountmock.entity.States;

import java.util.List;
import java.util.Optional;

public interface DistrictService {
    public List<District> findAll();
    public DistrictDTO findById(String id);

    public District findDistrictById(String id);

    public List<DistrictDTO> findDistrictByStateId(String id);
    public void save(List<DistrictDTO> districts, States states);
}
