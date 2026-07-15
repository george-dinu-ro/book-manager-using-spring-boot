package my.work.book.manager.controller;

import lombok.RequiredArgsConstructor;
import my.work.book.manager.entity.Book;
import my.work.book.manager.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/books")
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    List<Book> findAll(@RequestParam(required = false) String category) {
        return bookService.findAll(category);
    }

    @GetMapping("/{title}")
    ResponseEntity<Book> findByTitle(@PathVariable String title) {
        return bookService.findByTitle(title)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<Book> create(@RequestBody Book book) {
        return null;
    }

}
