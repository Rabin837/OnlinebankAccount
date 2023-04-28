package com.bitskraft.bankaccountmock.serviceImpl;

import com.bitskraft.bankaccountmock.customerexception.EntityNotFound;
import com.bitskraft.bankaccountmock.dto.DistrictDTO;
import com.bitskraft.bankaccountmock.entity.District;
import com.bitskraft.bankaccountmock.entity.States;
import com.bitskraft.bankaccountmock.repository.DistrictRepository;
import com.bitskraft.bankaccountmock.service.DistrictService;
import com.bitskraft.bankaccountmock.service.MunicipalityService;
import com.bitskraft.bankaccountmock.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl implements DistrictService {

    DistrictRepository districtRepository;
    MunicipalityService municipalityService;
    StateService stateService;

    @Autowired
    DistrictServiceImpl(DistrictRepository districtRepository, @Lazy MunicipalityService municipalityService, @Lazy StateService stateService) {
        this.districtRepository = districtRepository;
        this.municipalityService = municipalityService;
        this.stateService = stateService;
    }

    @Override
    public List<District> findAll() {
        return districtRepository.findAll();
    }

    @Override
    public DistrictDTO findById(String id) {
        DistrictDTO districtDTO = new DistrictDTO();
        District district = findDistrictById(id);
        districtDTO.setName(district.getName());
        return districtDTO;
    }

    @Override
    public District findDistrictById(String id) {
        District district = districtRepository.findById(id).orElseThrow(()->new EntityNotFound("District not found"));
        district.setName(district.getName());
        return district;
    }

    @Override
    public List<DistrictDTO> findDistrictByStateId(String id) {
        States state = stateService.findStateById(id);
        List<District> districtList = districtRepository.findAllByStates(state);
        return districtList.stream().map(district -> {
           return DistrictDTO.builder()
                    .id(district.getId())
                    .name(district.getName())
                    .build();
        }).collect(Collectors.toList());
    }
    @Override
    public void save(List<DistrictDTO> districts, States states) {
        for (DistrictDTO districtDTO : districts) {
            District district = new District();
            district.setId(UUID.randomUUID().toString());
            district.setName(districtDTO.getName());
            district.setStates(states);
            district = districtRepository.save(district);
            municipalityService.save(districtDTO.getMunicipalities(), district);
        }

    }
}
