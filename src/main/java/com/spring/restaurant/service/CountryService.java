package com.spring.restaurant.service;

import com.spring.restaurant.model.Country;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CountryService {

    Country saveCountry(Country country);

    Country updateCountry(Long catId, Country country);

    Country findCountryById(Long id);

    List<Country> allCountries();

    List<Country> findAllCountriesOrderDesc();

    void deleteCountry(Long catId);

}
