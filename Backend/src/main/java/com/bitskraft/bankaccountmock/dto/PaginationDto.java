package com.bitskraft.bankaccountmock.dto;

import com.bitskraft.bankaccountmock.entity.Customer;
import lombok.Data;

import java.util.List;

@Data
public class PaginationDto {
    private List<CustomerDto> customer;
    private long totalCustomerNumber;
    private long currentPageSize;
    private long currentPageNumber;
    private long totalPage;
}
