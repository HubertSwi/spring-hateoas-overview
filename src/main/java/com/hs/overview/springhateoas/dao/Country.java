package com.hs.overview.springhateoas.dao;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "countries")
@Entity
@Data
public class Country {
    @Id
    private Integer id;
    private String name;
    private String iso3;
    private String numericCode;
    private String iso2;
    private String phonecode;
    private String capital;
    private String currency;
    private String currencyName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Continent continent;

    private String subregion;
}
