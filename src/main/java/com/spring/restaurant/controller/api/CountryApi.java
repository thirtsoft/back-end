package com.spring.restaurant.controller.api;

import com.spring.restaurant.model.Country;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.spring.restaurant.util.Constants.APP_ROOT;

public interface CountryApi {

    @PostMapping(value = APP_ROOT + "/countries/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Country> createCountry(@RequestBody Country country);

    @PutMapping(value = APP_ROOT + "/countries/update/{countId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Country> updateCountry(@PathVariable("countId") Long countId, @RequestBody Country country);

    @GetMapping(value = APP_ROOT + "/countries/findById/{countId}",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Country> getCountryById(@PathVariable("countId") Long countId);

    // http://localhost:8080/api/allCategoies
    @GetMapping(value = APP_ROOT + "/countries/allCountries")
    ResponseEntity<List<Country>>  getAllCountry();

    @GetMapping(value = APP_ROOT + "/countries/searchAllCountriesOrderByIdDesc")
    ResponseEntity<List<Country>> getAllCountriesOrderDesc();

    @DeleteMapping(value = APP_ROOT + "/countries/delete/{countId}")
    void deleteCountryById(@PathVariable("countId") Long countId);
}
