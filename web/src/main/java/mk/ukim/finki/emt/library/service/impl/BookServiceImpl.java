package mk.ukim.finki.emt.library.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.library.model.Author;
import mk.ukim.finki.emt.library.model.Book;
import mk.ukim.finki.emt.library.model.dto.BookDto;
import mk.ukim.finki.emt.library.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.library.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.library.model.exceptions.NoMoreAvailableCopiesException;
import mk.ukim.finki.emt.library.repository.AuthorRepository;
import mk.ukim.finki.emt.library.repository.BookRepository;
import mk.ukim.finki.emt.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(AuthorNotFoundException::new);
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        Author author = authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(AuthorNotFoundException::new);
        book.setName(bookDto.getName());
        book.setAuthor(author);
        book.setCategory(bookDto.getCategory());
        book.setAvailableCopies(bookDto.getAvailableCopies());
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        int availableCopies = bookRepository.getAvailableCopiesById(id);
        if (availableCopies == 0) throw new NoMoreAvailableCopiesException();
        availableCopies--;
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        book.setAvailableCopies(availableCopies);
        bookRepository.save(book);
        return Optional.of(book);
    }
}
