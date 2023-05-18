package com.bitskraft.bankaccountmock.service;

import com.bitskraft.bankaccountmock.dto.response.BaseResponse;
import com.bitskraft.bankaccountmock.entity.Address;
import com.bitskraft.bankaccountmock.dto.AddressDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<Address> findAll();
    public Address findById(String id);
    public Address save(AddressDTO AddressDTOData);

    Address update(String addressId, AddressDTO permanentAddressDTOData);
}
