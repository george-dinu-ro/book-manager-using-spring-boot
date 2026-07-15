package my.work.book.manager.service;

import lombok.RequiredArgsConstructor;
import my.work.book.manager.entity.Book;
import my.work.book.manager.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> read() {
        return bookRepository.read();
    }

}
