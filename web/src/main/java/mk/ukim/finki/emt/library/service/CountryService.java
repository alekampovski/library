package mk.ukim.finki.emt.library.service;

import mk.ukim.finki.emt.library.model.Country;
import mk.ukim.finki.emt.library.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(CountryDto countryDto);
    void deleteById(Long id);
}
