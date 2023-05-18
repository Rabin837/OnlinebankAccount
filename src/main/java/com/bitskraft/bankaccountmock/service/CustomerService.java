package com.bitskraft.bankaccountmock.service;

import com.bitskraft.bankaccountmock.dto.CustomerCustomerAccountDto;
import com.bitskraft.bankaccountmock.dto.CustomerDto;
import com.bitskraft.bankaccountmock.dto.PaginationDto;
import com.bitskraft.bankaccountmock.dto.response.BaseResponse;
import com.bitskraft.bankaccountmock.entity.Address;
import com.bitskraft.bankaccountmock.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

public interface CustomerService {

    BaseResponse addCustomer(CustomerDto customerDto);

    CustomerDto getCustomer(String customerId);

    BaseResponse updateCustomer(String customerId, CustomerDto customerDto);

    BaseResponse deleteCustomerCustomerAccount(String customerId);

    PaginationDto getAllCustomer(Integer pageNumber, Integer pageSize);


    //code return to account implementation

    Customer returnCustomer(String customerId);

    CustomerCustomerAccountDto findByCifId(String cifId);

    //For test
Customer convertDtoToEntity(CustomerDto customerDto,Address permanentAddress,Address temporaryAddress);

}