package mk.ukim.finki.emt.library.web.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.library.model.Book;
import mk.ukim.finki.emt.library.model.dto.BookDto;
import mk.ukim.finki.emt.library.model.enumerations.Category;
import mk.ukim.finki.emt.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookRestController {

    private final BookService bookService;


    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/categories")
    public List<Category> listOfCategories() {
        return Arrays.asList(Category.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return bookService.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return bookService.save(bookDto).map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return bookService.edit(id, bookDto).map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
        if(bookService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/markAsTaken/{id}")
    public ResponseEntity<Book> markAsTaken(@PathVariable Long id) {
        return bookService.markAsTaken(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
