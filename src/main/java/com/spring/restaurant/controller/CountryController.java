package com.spring.restaurant.controller;

import com.spring.restaurant.controller.api.CountryApi;
import com.spring.restaurant.model.Country;
import com.spring.restaurant.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


// http://localhost:8080/api
@RestController
@CrossOrigin("http://localhost:4200")
//@RequestMapping("/api")
public class CountryController implements CountryApi {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public ResponseEntity<Country> createCountry(Country country) {
        Country countryResult = countryService.saveCountry(country);
        return new ResponseEntity<>(countryResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Country> updateCountry(Long countId, Country country) {
        country.setId(countId);
        Country countryUpdate = countryService.saveCountry(country);
        return new ResponseEntity<>(countryUpdate, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Country> getCountryById(Long countId) {
        Country countrySearch = countryService.findCountryById(countId);
        return new ResponseEntity<>(countrySearch, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Country>> getAllCountry() {
        List<Country> countryList = countryService.allCountries();
        return new ResponseEntity<>(countryList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Country>> getAllCountriesOrderDesc() {
        List<Country> countryList = countryService.findAllCountriesOrderDesc();
        return new ResponseEntity<>(countryList, HttpStatus.OK);
    }

    @Override
    public void deleteCountryById(Long countId) {
        countryService.deleteCountry(countId);

    }
}
