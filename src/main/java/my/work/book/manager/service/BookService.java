package my.work.book.manager.service;

import lombok.RequiredArgsConstructor;
import my.work.book.manager.entity.Book;
import my.work.book.manager.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> findAll(String category) {
        return Objects.isNull(category)
                ? this.bookRepository.findAll()
                : this.bookRepository.findByCategory(category);
    }

    public Optional<Book> findById(long id) {
        return this.bookRepository.findById(id);
    }

    public Book create(Book book) {
        return this.bookRepository.findByTitle(book.title()).isEmpty()
                ? this.bookRepository.create(book)
                : null;
    }

    public Book update(String title, Book book) {
        var index = this.bookRepository.getIndex(title);
        return bookExists(index)
                ? updateAndGet(index, title, book)
                : null;
    }

    public boolean delete(String title) {
        return this.bookRepository.delete(title);
    }

    private static boolean bookExists(int index) {
        return (index > -1);
    }

    private static Book getUpdatedBook(String title, Book book) {
        return Book.builder()
                .title(title)
                .author(book.author())
                .category(book.category())
                .build();
    }

    private Book updateAndGet(int index, String title, Book book) {
        var updateBook = getUpdatedBook(title, book);
        this.bookRepository.update(index, updateBook);
        return updateBook;
    }

}
