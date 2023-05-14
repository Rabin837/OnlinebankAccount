package com.bitskraft.bankaccountmock.controller;

import com.bitskraft.bankaccountmock.dto.CustomerAccountDto;
import com.bitskraft.bankaccountmock.dto.CustomerCustomerAccountDto;
import com.bitskraft.bankaccountmock.dto.CustomerDto;
import com.bitskraft.bankaccountmock.dto.PaginationDto;
import com.bitskraft.bankaccountmock.dto.response.BaseResponse;
import com.bitskraft.bankaccountmock.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("customer/")

public class CustomerController {
    @Autowired
    private CustomerService customerServices;


    //Add Customer
    @PostMapping("add")
    public ResponseEntity<BaseResponse> add(@RequestBody @Valid  CustomerDto customerDto) {
    return ResponseEntity.ok(this.customerServices.addCustomer(customerDto));
    }
    //finding single customer
    @GetMapping("find/{customerId}")
    public ResponseEntity<CustomerDto> get(@PathVariable String customerId) {

        return ResponseEntity.ok(customerServices.getCustomer(customerId));


    }
    //Updating customer details
    @PutMapping("update/{customerId}")
    public ResponseEntity<BaseResponse> update(@PathVariable String customerId,@RequestBody @Valid CustomerDto customerDto) {

        return ResponseEntity.ok(customerServices.updateCustomer(customerId, customerDto));

    }
    //delete single customer
    @DeleteMapping("delete/{customerId}")
    public ResponseEntity<BaseResponse> delete(@PathVariable String customerId) {

        return  ResponseEntity.ok(customerServices.deleteCustomerCustomerAccount(customerId));

    }
    //Find All Customer
    @GetMapping("findAll/{pageNumber}/{pageSize}")
    public ResponseEntity<PaginationDto> getAllCustomer(@PathVariable Integer pageNumber , @PathVariable Integer pageSize) {

        return  ResponseEntity.ok(customerServices.getAllCustomer(pageNumber,pageSize));

    }
    //find customer details by customer information id
    @GetMapping("getByCifId")
    public ResponseEntity<CustomerCustomerAccountDto> getByCifId(@RequestParam(name="cifId") String cifId){

        return ResponseEntity.ok(customerServices.findByCifId(cifId));
    }
}
