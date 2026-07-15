package my.work.book.manager.controller;

import my.work.book.manager.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RequestMapping("/api/v1/books")
@RestController
public class BookController {

    @GetMapping
    List<Book> read() {
        return Collections.emptyList();
    }

}
