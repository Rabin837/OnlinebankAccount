package com.bitskraft.bankaccountmock.service;

import com.bitskraft.bankaccountmock.dto.DistrictDTO;
import com.bitskraft.bankaccountmock.dto.MunicipalityDTO;
import com.bitskraft.bankaccountmock.entity.District;
import com.bitskraft.bankaccountmock.entity.Municipality;

import java.util.List;
import java.util.Optional;

public interface MunicipalityService {
    public List<Municipality> findAll();
    public MunicipalityDTO findById(String id);

    public Municipality findMunicipalityById(String id);
    public List<MunicipalityDTO> findMunicipalityByDistrictId(String id);

    public void save(List<MunicipalityDTO> municipalityDTOS, District district);
}
