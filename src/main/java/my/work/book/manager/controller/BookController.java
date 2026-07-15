package my.work.book.manager.controller;

import lombok.RequiredArgsConstructor;
import my.work.book.manager.entity.Book;
import my.work.book.manager.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/books")
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    List<Book> read() {
        return bookService.read();
    }

    @GetMapping("/{title}")
    ResponseEntity<Book> read(@PathVariable String title) {
        return null;
    }

}
