package mk.ukim.finki.emt.library.service;

import mk.ukim.finki.emt.library.model.Book;
import mk.ukim.finki.emt.library.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> save(BookDto bookDto);
    Optional<Book> edit(Long id, BookDto bookDto);
    Optional<Book> markAsTaken(Long id);
    void deleteById(Long id);
}
