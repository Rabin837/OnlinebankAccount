package com.bitskraft.bankaccountmock.controller;

import com.bitskraft.bankaccountmock.dto.DistrictDTO;
import com.bitskraft.bankaccountmock.dto.MunicipalityDTO;
import com.bitskraft.bankaccountmock.dto.response.BaseResponse;
import com.bitskraft.bankaccountmock.entity.District;
import com.bitskraft.bankaccountmock.service.DistrictService;
import com.bitskraft.bankaccountmock.service.MunicipalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("district/")
public class DistrictController {
    @Autowired
    DistrictService districtService;
    @Autowired
    MunicipalityService municipalityService;

    @GetMapping("showAll")
    public ResponseEntity<List<District>> findAll(){
        List<District> districtList=districtService.findAll();
        return new ResponseEntity<>(districtList,HttpStatus.OK);
    }
    @GetMapping("/show/{id}")
    public ResponseEntity<DistrictDTO> findById(@PathVariable String id){
        DistrictDTO districtDTO=districtService.findById(id);
        return new ResponseEntity<>(districtDTO,HttpStatus.OK);
    }
    @GetMapping("districtFromState/{id}")
    public ResponseEntity<List<DistrictDTO>> findDistrictByStateId(@PathVariable String id){
        List<DistrictDTO> districtDTO=districtService.findDistrictByStateId(id);
        return new ResponseEntity<>(districtDTO,HttpStatus.OK);
    }
    @GetMapping("municipalityFromDistrict/{id}")
    public ResponseEntity<List<MunicipalityDTO>> findMunicipalityByDistrictId(@PathVariable String id){
        List<MunicipalityDTO> municipalityDTOS=municipalityService.findMunicipalityByDistrictId(id);
        return new ResponseEntity<>(municipalityDTOS,HttpStatus.OK);
    }

}
