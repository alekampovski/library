package mk.ukim.finki.emt.library.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.library.model.Country;
import mk.ukim.finki.emt.library.model.dto.CountryDto;
import mk.ukim.finki.emt.library.repository.CountryRepository;
import mk.ukim.finki.emt.library.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
         Country country = new Country(countryDto.getName(), countryDto.getContinent());
       countryRepository.save(country);
       return Optional.of(country);
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
