package my.work.book.manager.controller;

import lombok.RequiredArgsConstructor;
import my.work.book.manager.entity.Book;
import my.work.book.manager.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RequestMapping("/api/v1/books")
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    List<Book> findAll(@RequestParam(required = false) String category) {
        return bookService.findAll(category);
    }

    @GetMapping("/{id}")
    ResponseEntity<Book> findById(@PathVariable int id) {
        return this.bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<Book> create(@RequestBody Book book) {
        var createdBook = this.bookService.create(book);
        return Objects.nonNull(createdBook)
                ? ResponseEntity.status(HttpStatus.CREATED).body(createdBook)
                : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PutMapping("/{id}")
    ResponseEntity<Book> update(@PathVariable int id, @RequestBody Book book) {
        var updatedBook = this.bookService.update(id, book);
        return Objects.nonNull(updatedBook)
                ? ResponseEntity.ok(updatedBook)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable int id) {
        return this.bookService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
