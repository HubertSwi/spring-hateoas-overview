package com.hs.overview.springhateoas.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContinentRepository extends CrudRepository<Continent, Integer> {
    List<Continent> findAll();
}
