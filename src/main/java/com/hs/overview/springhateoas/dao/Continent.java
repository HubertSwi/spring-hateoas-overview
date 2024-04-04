package com.hs.overview.springhateoas.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "continents")
@Entity
@Data
public class Continent {
    @Id
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "continent", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Country> countries;
}
