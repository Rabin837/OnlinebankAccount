package com.bitskraft.bankaccountmock.dto;

import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class CustomerCustomerAccountDto {
    private CustomerDto customerDto;
    private List<CustomerAccountDto> customerAccountDtoList;

}
