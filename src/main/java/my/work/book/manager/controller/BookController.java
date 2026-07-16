package my.work.book.manager.controller;

import lombok.RequiredArgsConstructor;
import my.work.book.manager.entity.BookEntity;
import my.work.book.manager.request.BookRequest;
import my.work.book.manager.service.BookService;
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

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RequestMapping("/api/v1/books")
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    List<BookEntity> findAll(@RequestParam(required = false) String category) {
        return bookService.findAll(category);
    }

    @GetMapping("/{id}")
    ResponseEntity<BookEntity> findById(@PathVariable int id) {
        return this.bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<BookEntity> create(@RequestBody BookRequest bookRequest) {
        var id = this.bookService.create(bookRequest);
        return ResponseEntity.created(URI.create("/api/v1/books/" + id)).build();
    }

    @PutMapping("/{id}")
    ResponseEntity<BookEntity> update(@PathVariable int id, @RequestBody BookRequest bookRequest) {
        var updatedBook = this.bookService.update(id, bookRequest);
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
