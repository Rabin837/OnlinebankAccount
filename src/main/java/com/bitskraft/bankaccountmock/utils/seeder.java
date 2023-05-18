package com.bitskraft.bankaccountmock.utils;

import com.bitskraft.bankaccountmock.dto.CountryDTO;
import com.bitskraft.bankaccountmock.service.CountryService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

@Slf4j
public class seeder {
    @Autowired
    CountryService countryService;

    @Bean
    CommandLineRunner runner() {
        return args -> {
            // read json and write to db
            try {
                if(countryService.countAll() == 0) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                            Objects.requireNonNull(this.getClass().
                                    getResourceAsStream("/nepal-data.json"))));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    CountryDTO countryDetails = new Gson().fromJson(stringBuilder.toString(), CountryDTO.class);
                    countryService.save(countryDetails);
                    log.info("Address saved");
                }
            }
            catch (Exception e) {
                log.error(e.getMessage());
            }
        };
    }
}
