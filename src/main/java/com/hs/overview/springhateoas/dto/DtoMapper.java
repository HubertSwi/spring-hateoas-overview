package com.hs.overview.springhateoas.dto;

import com.hs.overview.springhateoas.dao.Continent;
import com.hs.overview.springhateoas.dao.Country;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DtoMapper {

    public ContinentDto mapContinent(Continent c) {
        return ContinentDto.builder()
                .id(c.getId())
                .name(c.getName())
                .countries(null)
                .build();
    }

    public ContinentDto mapContinentWithCountriesNames(Continent c) {
        return ContinentDto.builder()
                .id(c.getId())
                .name(c.getName())
                .countries(c.getCountries().stream().map(this::mapCountryName).collect(Collectors.toList()))
                .build();
    }

    public ContinentDto.CountryDto mapCountry(Country country) {
        return ContinentDto.CountryDto.builder()
                .id(country.getId())
                .name(country.getName())
                .iso3(country.getIso3())
                .numericCode(country.getNumericCode())
                .iso2(country.getIso2())
                .phonecode(country.getPhonecode())
                .capital(country.getCapital())
                .currency(country.getCurrency())
                .currencyName(country.getCurrencyName())
                .subregion(country.getSubregion())
                .build();
    }

    private ContinentDto.CountryDto mapCountryName(Country country) {
        return ContinentDto.CountryDto.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }
}
