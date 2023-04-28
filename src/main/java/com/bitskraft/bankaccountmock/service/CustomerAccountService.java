package com.bitskraft.bankaccountmock.service;


import com.bitskraft.bankaccountmock.dto.CustomerAccountDto;
import com.bitskraft.bankaccountmock.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface CustomerAccountService {
    BaseResponse addAccount(String customerId,CustomerAccountDto customerAccountDto);

    ResponseEntity<List<CustomerAccountDto>> getAccounts(String customerId);
    CustomerAccountDto getSpecificAccount(String customerId,String customerAccountId);

    BaseResponse deleteAccount(String customerId);

    ResponseEntity<List<CustomerAccountDto>> getAllAccount();

    BaseResponse updateAccount(String customerId,String customerAccountId, CustomerAccountDto customerAccountDto);

    CustomerAccountDto findByAccNum(String customerId,String accountNumber);
    CustomerAccountDto findByAccNum(String accountNumber);

//return customer account list to customer find by cif id
    List<CustomerAccountDto> findAccountByCustomerId(String customerId);
}
