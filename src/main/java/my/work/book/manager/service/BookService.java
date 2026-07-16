package my.work.book.manager.service;

import lombok.RequiredArgsConstructor;
import my.work.book.manager.entity.BookEntity;
import my.work.book.manager.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookEntity> findAll(String category) {
        return Objects.isNull(category)
                ? this.bookRepository.findAll()
                : this.bookRepository.findByCategory(category);
    }

    public Optional<BookEntity> findById(long id) {
        return this.bookRepository.findById(id);
    }

    public BookEntity create(BookEntity bookEntity) {
        return this.bookRepository.findById(bookEntity.id()).isEmpty()
                ? this.bookRepository.create(bookEntity)
                : null;
    }

    public BookEntity update(int id, BookEntity bookEntity) {
        var index = this.bookRepository.getIndex(id);
        return bookExists(index)
                ? updateAndGet(index, id, bookEntity)
                : null;
    }

    public boolean delete(int id) {
        return this.bookRepository.delete(id);
    }

    private static boolean bookExists(int index) {
        return (index > -1);
    }

    private static BookEntity getUpdatedBook(int id, BookEntity bookEntity) {
        return BookEntity.builder()
                .id(id)
                .title(bookEntity.title())
                .author(bookEntity.author())
                .category(bookEntity.category())
                .rating(bookEntity.rating())
                .build();
    }

    private BookEntity updateAndGet(int index, int id, BookEntity bookEntity) {
        var updateBook = getUpdatedBook(id, bookEntity);
        this.bookRepository.update(index, updateBook);
        return updateBook;
    }

}
