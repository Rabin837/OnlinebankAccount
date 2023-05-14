package com.bitskraft.bankaccountmock.serviceImpl;



import com.bitskraft.bankaccountmock.customerexception.EntityNotFound;
import com.bitskraft.bankaccountmock.dto.CountryDTO;
import com.bitskraft.bankaccountmock.entity.Country;
import com.bitskraft.bankaccountmock.repository.CountryRepository;
import com.bitskraft.bankaccountmock.service.CountryService;
import com.bitskraft.bankaccountmock.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service public class CountryServiceImpl implements CountryService {
    @Autowired
    private  CountryRepository countryRepository;
    @Autowired
    private  StateService stateService;


    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public CountryDTO findById(int id) {
        Country country = findCountryById(id);
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName(country.getName());
        return countryDTO;
    }

    @Override
    public Country findCountryById(int id) {
        return countryRepository.findById(id)
                .orElseThrow(() ->  new EntityNotFound("Country with id "+ id + " not found"));
    }

    @Override
    public void save(CountryDTO countryDTO) {
        Country country = new Country();
//        country.setId(UUID.randomUUID().toString());
        country.setName(countryDTO.getName());
        country = countryRepository.save(country);

        stateService.save(countryDTO.getStates(), country);
    }

    @Override
    public long countAll() {
        return countryRepository.count();
    }
}
