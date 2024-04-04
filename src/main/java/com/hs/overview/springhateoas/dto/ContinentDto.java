package com.hs.overview.springhateoas.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContinentDto extends RepresentationModel<ContinentDto> {
    private Integer id;
    private String name;
    private List<CountryDto> countries;

    @Data
    @EqualsAndHashCode(callSuper = true)
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CountryDto extends RepresentationModel<CountryDto> {
        private Integer id;
        private String name;
        private String iso3;
        private String numericCode;
        private String iso2;
        private String phonecode;
        private String capital;
        private String currency;
        private String currencyName;
        private String region;
        private String subregion;
    }

}
