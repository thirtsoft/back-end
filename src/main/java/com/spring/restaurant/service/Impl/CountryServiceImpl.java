package com.spring.restaurant.service.Impl;

import com.spring.restaurant.deo.CountryRepository;
import com.spring.restaurant.model.Category;
import com.spring.restaurant.model.Country;
import com.spring.restaurant.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country updateCountry(Long catId, Country country) {
        if (!countryRepository.existsById(catId)) {
            log.error("Country not found");
        }

        Country updateCountry= countryRepository.findById(catId).get();
        updateCountry.setName(country.getName());
        updateCountry.setCode(country.getCode());

        return countryRepository.save(updateCountry);
    }

    @Override
    public Country findCountryById(Long id) {
        return countryRepository.findById(id).get();
    }

    @Override
    public List<Country> allCountries() {
        return countryRepository.findAll();
    }

    @Override
    public List<Country> findAllCountriesOrderDesc() {
        return countryRepository.findByOrderByIdDesc();
    }

    @Override
    public void deleteCountry(Long catId) {
        if (catId == null) {
            log.error("Country not found");
        }
        countryRepository.deleteById(catId);

    }
}
