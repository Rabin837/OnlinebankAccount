package com.bitskraft.bankaccountmock.controller;


import com.bitskraft.bankaccountmock.dto.CustomerAccountDto;
import com.bitskraft.bankaccountmock.dto.response.BaseResponse;
import com.bitskraft.bankaccountmock.service.CustomerAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("customer-account/")
public class CustomerAccountController {
    @Autowired
    private CustomerAccountService customerAccountServices;

    //Add Customer
    @PostMapping("add/{customerId}")
    public ResponseEntity<BaseResponse> add(@PathVariable String customerId, @RequestBody @Valid CustomerAccountDto customerAccountDto) {
        return ResponseEntity.ok(this.customerAccountServices.addAccount(customerId,customerAccountDto));
    }

    //finding single customer account information by customer id(referenced id)
    @GetMapping("get/{customerId}")
    public ResponseEntity<List<CustomerAccountDto>> get(@PathVariable String customerId) {
        return customerAccountServices.getAccounts(customerId);
    }
    //finding specific account of corresponding customer
    @GetMapping("get/{customerId}/{customerAccountId}")
    public ResponseEntity<CustomerAccountDto> getSpecific(@PathVariable String customerId,@PathVariable String customerAccountId) {
        return ResponseEntity.ok(customerAccountServices.getSpecificAccount(customerId,customerAccountId));
    }
    //Update Account
    @PutMapping("update/{customerId}/{customerAccountId}")
    public ResponseEntity<BaseResponse> update(@PathVariable String customerId, @PathVariable String customerAccountId, @RequestBody @Valid CustomerAccountDto customerAccountDto) {
        return ResponseEntity.ok(customerAccountServices.updateAccount(customerId, customerAccountId, customerAccountDto));
    }

    //Deleting customer account by using customer id and customer account id
    @DeleteMapping("delete/{customerAccountId}")
    public ResponseEntity<BaseResponse> delete(@PathVariable String customerAccountId) {
        return ResponseEntity.ok(customerAccountServices.deleteAccount(customerAccountId));
    }

    //finding all customer accounts

    @GetMapping("getAll")
    public ResponseEntity<List<CustomerAccountDto>> getAllAccount() {
        return customerAccountServices.getAllAccount();

    }
    //find customer details by account number for backend
    @GetMapping("getByAccNum/{customerId}")
    public ResponseEntity<CustomerAccountDto> getByAccNumber(@PathVariable String customerId ,@RequestParam(name="accountNumber") String accountNumber){

        return ResponseEntity.ok(customerAccountServices.findByAccNum(customerId,accountNumber));
    }
    //find customer details by account number for frontend
    @GetMapping("getByAccNum")
    public ResponseEntity<CustomerAccountDto> getByAccountNumber(@RequestParam(name="accountNumber") String accountNumber){

        return ResponseEntity.ok(customerAccountServices.findByAccNum(accountNumber));
    }

}
