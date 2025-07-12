package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.exception.CountryNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {

    @GetMapping("/country")
    public Country getCountryIndia() {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        return context.getBean("country", Country.class);
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        return context.getBean("countryList", ArrayList.class);
    }

    @GetMapping("/countries/{code}")
    public Country getCountryByCode(@PathVariable String code) {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countryList = context.getBean("countryList", ArrayList.class);

        for (Country country : countryList) {
            if (country.getCode().equalsIgnoreCase(code)) {
                return country;
            }
        }

        throw new CountryNotFoundException(code);
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<String> handleCountryNotFound(CountryNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
