package com.bitskraft.bankaccountmock.serviceImpl;

import com.bitskraft.bankaccountmock.customerexception.EntityNotFound;
import com.bitskraft.bankaccountmock.dto.AddressDTO;

import com.bitskraft.bankaccountmock.entity.*;
import com.bitskraft.bankaccountmock.repository.AddressRepository;
import com.bitskraft.bankaccountmock.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {
    AddressRepository addressRepository;
    CountryService countryService;
    StateService stateService;
    DistrictService districtService;
    MunicipalityService municipalityService;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, CountryService countryService,
                              StateService stateService, DistrictService districtService, MunicipalityService municipalityService) {
        this.addressRepository = addressRepository;
        this.countryService = countryService;
        this.stateService = stateService;
        this.districtService = districtService;
        this.municipalityService = municipalityService;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(String id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new EntityNotFound("Address not found"));
        return address;
    }

    @Override
    public Address save(AddressDTO addressDTOData) {
        Address address = this.convertDtoToEntity(addressDTOData);

        return address;
    }

    @Override
    public Address update(String addressId, AddressDTO addressDTOData) {
        Address address=addressRepository.findById(addressId).orElseThrow(()->new EntityNotFound("Address not found"));
        Address newAddress=this.convertDtoToEntity(addressDTOData);

        address.setAddressId(addressId);//old id
        address.setCountry(newAddress.getCountry());
        address.setStates(newAddress.getStates());
        address.setDistricts(newAddress.getDistricts());
        address.setMunicipalities(newAddress.getMunicipalities());
        return address;
    }

    private Address convertDtoToEntity(AddressDTO addressDTO) {
        Address address = new Address();

        address.setAddressId(UUID.randomUUID().toString());
        Country country = countryService.findCountryById(addressDTO.getCountry());
        States states = stateService.findStateById(addressDTO.getStates());
        District district = districtService.findDistrictById(addressDTO.getDistricts());
        Municipality municipality = municipalityService.findMunicipalityById(addressDTO.getMunicipalities());

        address.setCountry(country);
        address.setStates(states);
        address.setDistricts(district);
        address.setMunicipalities(municipality);
return  address;
    }

}