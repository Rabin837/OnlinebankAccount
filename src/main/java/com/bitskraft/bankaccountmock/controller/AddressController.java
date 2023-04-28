package com.bitskraft.bankaccountmock.controller;

import com.bitskraft.bankaccountmock.dto.AddressDTO;
import com.bitskraft.bankaccountmock.dto.response.BaseResponse;
import com.bitskraft.bankaccountmock.entity.Address;
import com.bitskraft.bankaccountmock.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address/")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @GetMapping("showAll")
    public ResponseEntity<List<Address>> findAll() {
        List<Address> addresses = addressService.findAll();
      return new ResponseEntity<>(addresses,HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Address> save(@RequestBody AddressDTO addressDTO) {
         ;
        return ResponseEntity.ok(addressService.save(addressDTO));

    }
}
