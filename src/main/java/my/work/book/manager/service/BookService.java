package my.work.book.manager.service;

import lombok.RequiredArgsConstructor;
import my.work.book.manager.entity.BookEntity;
import my.work.book.manager.mapper.RequestToEntityMapper;
import my.work.book.manager.repository.BookRepository;
import my.work.book.manager.request.BookRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final RequestToEntityMapper requestToEntityMapper;

    public List<BookEntity> findAll(String category) {
        return Objects.isNull(category)
                ? this.bookRepository.findAll()
                : this.bookRepository.findByCategory(category);
    }

    public Optional<BookEntity> findById(long id) {
        return this.bookRepository.findById(id);
    }

    public long create(BookRequest bookRequest) {
        var bookEntity = requestToEntityMapper.toEntity(bookRequest);
        return this.bookRepository.create(bookEntity);
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
