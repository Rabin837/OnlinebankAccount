package com.bitskraft.bankaccountmock.controller;

import com.bitskraft.bankaccountmock.dto.StatesDTO;
import com.bitskraft.bankaccountmock.entity.States;
import com.bitskraft.bankaccountmock.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("state/")
public class StateController {
    @Autowired
    StateService stateService;
    @GetMapping("showAll")
    public ResponseEntity<List<States>> finAll(){
        List<States> statesList=stateService.findAll();
        return  new ResponseEntity<>(statesList, HttpStatus.OK);
    }

    @GetMapping("/stateDetails/{id}")
    public ResponseEntity<StatesDTO> finById(@PathVariable("id")String id){
       return new ResponseEntity<>(stateService.findById(id),HttpStatus.OK);
    }

}
