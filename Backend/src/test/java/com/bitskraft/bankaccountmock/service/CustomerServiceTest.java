package com.bitskraft.bankaccountmock.service;

import com.bitskraft.bankaccountmock.dto.AddressDTO;
import com.bitskraft.bankaccountmock.dto.CustomerDto;
import com.bitskraft.bankaccountmock.dto.response.BaseResponse;
import com.bitskraft.bankaccountmock.entity.Address;
import com.bitskraft.bankaccountmock.entity.Customer;
import com.bitskraft.bankaccountmock.repository.AddressRepository;
import com.bitskraft.bankaccountmock.repository.CustomerRepository;

import com.bitskraft.bankaccountmock.serviceImpl.AddressServiceImpl;
import com.bitskraft.bankaccountmock.serviceImpl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.mockito.MockitoAnnotations;

import javax.persistence.*;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomerServiceTest {
    /*
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AddressRepository addressRepository;

    CountryService countryService;

    StateService stateService;

    DistrictService districtService;

    MunicipalityService municipalityService;
    @InjectMocks

    private CustomerService customerService = new CustomerServiceImpl(customerRepository); // need to declare an appropriate  constructor in the EmployeeServiceImpl ,

    @MockBean
    private AddressService addressService = new AddressServiceImpl(addressRepository,countryService,stateService,districtService,municipalityService); // need to declare an appropriate  constructor in the EmployeeServiceImpl ,

//    @BeforeAll
//     public void init() {
//
//        MockitoAnnotations.openMocks(this);
//    }



    @Test
    void getCustomer() {
        System.out.println(".....mmmmmmmmmmmmmm "+customerRepository);

        Customer customer = new Customer();



        customer.setCustomerId(UUID.randomUUID().toString());
        customer.setName("kalakmalla");
        customer.setDob("2023-03-07T18:15:00.000Z");
        customer.setGender("male");
        customer.setPhone("+977 1212121218");
        customer.setEmail("kalak@fu.com");



    AddressDTO permanentAddressDto=new AddressDTO();
    AddressDTO temporaryAddressDto=new AddressDTO();

      //  permanentAddress.setAddressId(UUID.randomUUID().toString());
    permanentAddressDto.setCountry(1);
    permanentAddressDto.setStates("db548c2b-9d2c-41aa-9c9b-f434c32b72f1");
    permanentAddressDto.setDistricts("bc69788c-2ec2-46ea-bd53-ce49a03d85b1");
    permanentAddressDto.setMunicipalities("12e9fbe3-d9aa-455e-93cb-e65fa8e0fec5");

   // temporaryAddress.setAddressId(UUID.randomUUID().toString());
    temporaryAddressDto.setCountry(1);
    temporaryAddressDto.setStates("db548c2b-9d2c-41aa-9c9b-f434c32b72f1");
    temporaryAddressDto.setDistricts("bc69788c-2ec2-46ea-bd53-ce49a03d85b1");
    temporaryAddressDto.setMunicipalities("12e9fbe3-d9aa-455e-93cb-e65fa8e0fec5");

    Address permanentAddressReturn = addressService.save(permanentAddressDto);

    Address temporaryAddressReturn = addressService.save(temporaryAddressDto);



customer.setPermanentAddress(permanentAddressReturn);
customer.setTemporaryAddress(temporaryAddressReturn);

        customer.setNationality("Nepali");
        customer.setFatherName("fff");
        customer.setMotherName("mmmm");
        customer.setGrandFatherName("ggggg");
        customer.setCitizenshipNumber("121234");
        customer.setCitizenshipFrontImagePath("/fkf/dwd.png");
        customer.setCitizenshipBackImagePath("/back/dwd.png");
        customer.setPassportNumber("12sss12");
        customer.setPassportImagePath("/pass/fkdk.png");
        customer.setProfileImagePath("/pro/dfd.png");
        customer.setCifId("1212");
        customer.setBranch("kalil");
        customer.setBranchCode("b1");
        customer.setCustomerAddedDate("03/30/2023 03:44:45");
        customer.setCustomerUpdatedDate(null);
        System.out.println("....."+customer);

customerRepository.save(customer);
        System.out.println(".....mmmmmmmmmmmmmm "+customerRepository);


when(customerRepository.findById(anyString())).thenReturn(Optional.of(customer));
       CustomerDto customer1 = customerService.getCustomer("2023-03-07T18:15:00.000Z");

        assertEquals("kalakmalla", customer1.getName());

    }
    */
}