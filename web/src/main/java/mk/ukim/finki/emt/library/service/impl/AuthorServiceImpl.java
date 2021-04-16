package mk.ukim.finki.emt.library.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.library.model.Author;
import mk.ukim.finki.emt.library.model.Country;
import mk.ukim.finki.emt.library.model.dto.AuthorDto;
import mk.ukim.finki.emt.library.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.library.repository.AuthorRepository;
import mk.ukim.finki.emt.library.repository.CountryRepository;
import mk.ukim.finki.emt.library.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;


    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country country = countryRepository.findById(authorDto.getCountry())
                .orElseThrow(CountryNotFoundException::new);
        Author author = new Author(authorDto.getName(), authorDto.getSurname(), country);
        authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
