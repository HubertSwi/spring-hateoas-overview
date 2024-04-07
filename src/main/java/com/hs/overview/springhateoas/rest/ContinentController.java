package com.hs.overview.springhateoas.rest;

import com.hs.overview.springhateoas.dao.ContinentRepository;
import com.hs.overview.springhateoas.dao.CountryRepository;
import com.hs.overview.springhateoas.dto.ContinentDto;
import com.hs.overview.springhateoas.dto.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/continents")
public class ContinentController {

    @Autowired
    ContinentRepository continentRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    DtoMapper mapper;

    /*
        EntityModel - always contain a payload and thus allows to reason about the type arrangement on the sole instance,
        CollectionModel - underlying collection can be empty.

        Due to Java’s type erasure, we cannot actually detect that a CollectionModel<ContinentDto> countriesDto is empty
        is actually a CollectionModel<ContinentDto> because all we see is the runtime instance and an empty collection.
        That missing type information can be added to the model by either adding it to the
        empty instance on construction via CollectionModel.empty(ContinentDto.class) or as fallback like in this case.
     */
    @GetMapping
    public CollectionModel<ContinentDto> getAllContinents() {
        List<ContinentDto> countriesDto = continentRepository.findAll().stream()
                .map(mapper::mapContinent)
                .map(c -> c.add(linkTo(methodOn(ContinentController.class).getContinentById(c.getId())).withSelfRel()))
                .collect(Collectors.toList());
        Link link = linkTo(methodOn(ContinentController.class).getAllContinents()).withSelfRel();
        return CollectionModel.of(countriesDto, link).withFallbackType(ContinentDto.class);
    }

    /*
        The implementation of methods linkTo() and methodOn() is very costly.
        Profiling the application showed significant CPU hotspots in just generating the links.

        That one generates a proxy instance of the controller class, adds some method interceptor to introspect parameters,
        and does some more reflection stuff in the background.
        There were plenty of attempts to improve the implementation, e.g. by caching those proxy classes aggressively,
        however for our case this was not enough.

        Speed up: linkTo() + methodOn() -> linkTo() -> pure StringBuilder
     */
    @GetMapping("/{continentId}")
    public ResponseEntity<ContinentDto> getContinentById(@PathVariable Integer continentId) {
        ContinentDto continentDto = mapper.mapContinentWithCountriesNames(
                continentRepository.findById(continentId).orElseThrow(() -> new IllegalArgumentException("Continent not found")));

        // 1st attempt with poor performance:
//        continentDto.getCountries().forEach(c -> c.add(linkTo(methodOn(ContinentController.class).getCountryById(continentId, c.getId())).withSelfRel()));
//        continentDto.add(linkTo(methodOn(ContinentController.class).getContinentById(continentId)).withSelfRel());
//        continentDto.add(linkTo(methodOn(ContinentController.class).getContinentById(continentId + 1)).withRel("next"));
//        continentDto.add(linkTo(methodOn(ContinentController.class).getContinentById(continentId - 1)).withRel("previous"));

        // 2nd attempt - faster because without methodOn()
        continentDto.getCountries().forEach(c -> c.add(
                linkTo(ContinentController.class).slash(continentId).slash("countries").slash(c.getId()).withSelfRel()));
        continentDto.add(linkTo(ContinentController.class).slash(continentId).withSelfRel());
        if (continentId < 6) {
            continentDto.add(linkTo(ContinentController.class).slash(continentId + 1).withRel("next"));
        }
        if (continentId > 1) {
            continentDto.add(linkTo(ContinentController.class).slash(continentId - 1).withRel("previous"));
        }

        // 3rd attempt could be faster if we get rid of both methodOn() and linkTo().
        // To do date we could use direct link building with help of BasicLinkBuilder or even StringBuilder.

        return ResponseEntity.ok(continentDto);
    }

    @GetMapping("/{continentId}/countries/{countryId}")
    public ResponseEntity<ContinentDto.CountryDto> getCountryById(@PathVariable Integer continentId, @PathVariable Integer countryId) {
        ContinentDto.CountryDto dto = mapper.mapCountry(countryRepository.findById(countryId).orElseThrow());
        dto.add(linkTo(methodOn(ContinentController.class).getCountryById(continentId, countryId)).withSelfRel());
        return ResponseEntity.ok(dto);
    }
}
