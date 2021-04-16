package mk.ukim.finki.emt.library.service;

import mk.ukim.finki.emt.library.model.Author;
import mk.ukim.finki.emt.library.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> save(AuthorDto authorDto);
    void deleteById(Long id);
}
