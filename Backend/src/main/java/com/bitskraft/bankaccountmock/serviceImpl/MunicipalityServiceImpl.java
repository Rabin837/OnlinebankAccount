package com.bitskraft.bankaccountmock.serviceImpl;

import com.bitskraft.bankaccountmock.customerexception.EntityNotFound;
import com.bitskraft.bankaccountmock.dto.MunicipalityDTO;
import com.bitskraft.bankaccountmock.entity.District;
import com.bitskraft.bankaccountmock.entity.Municipality;
import com.bitskraft.bankaccountmock.repository.MunicipalityRepository;
import com.bitskraft.bankaccountmock.service.DistrictService;
import com.bitskraft.bankaccountmock.service.MunicipalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MunicipalityServiceImpl implements MunicipalityService {
    MunicipalityRepository municipalityRepository;
    DistrictService districtService;
    @Autowired
    MunicipalityServiceImpl(MunicipalityRepository municipalityRepository, DistrictService districtService){
        this.municipalityRepository=municipalityRepository;
        this.districtService=districtService;
    }
    @Override
    public List<Municipality> findAll() {
        return municipalityRepository.findAll();
    }

    @Override
    public MunicipalityDTO findById(String id) {
         MunicipalityDTO municipalityDTO=new MunicipalityDTO();
         Municipality municipality=findMunicipalityById(id);
         municipalityDTO.setName(municipality.getName());
         municipalityDTO.setType(municipality.getType());
         return municipalityDTO;
    }

    @Override
    public Municipality findMunicipalityById(String id) {
        Municipality municipality=municipalityRepository.findById(id).orElseThrow(()->new EntityNotFound("Municipality not found"));
        municipality.setName(municipality.getName());
        municipality.setType(municipality.getType());
        return municipality;
    }

    @Override
    public List<MunicipalityDTO> findMunicipalityByDistrictId(String id) {
        District district=districtService.findDistrictById(id);
        List<Municipality> municipalityList=municipalityRepository.findAllByDistricts(district);
        return municipalityList.stream().map(municipality -> {
            return MunicipalityDTO.builder()
                    .id(municipality.getId())
                    .name(municipality.getName())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public void save(List<MunicipalityDTO> municipalityDTOS, District district) {
           for(MunicipalityDTO municipalityDTO:municipalityDTOS){
               Municipality municipality=new Municipality();
               municipality.setId(UUID.randomUUID().toString());
               municipality.setName(municipalityDTO.getName());
               municipality.setType(municipalityDTO.getType());
               municipality.setDistricts(district);
               municipalityRepository.save(municipality);
           }
    }
}
