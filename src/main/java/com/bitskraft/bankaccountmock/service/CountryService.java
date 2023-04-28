package com.bitskraft.bankaccountmock.service;



import com.bitskraft.bankaccountmock.dto.CountryDTO;
import com.bitskraft.bankaccountmock.entity.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();

    CountryDTO findById(int id);

    Country findCountryById(int id);

    void save(CountryDTO countryDTO);

    long countAll();
}
