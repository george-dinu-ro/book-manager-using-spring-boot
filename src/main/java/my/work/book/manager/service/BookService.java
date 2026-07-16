package my.work.book.manager.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import my.work.book.manager.entity.BookEntity;
import my.work.book.manager.exception.BookNotFoundException;
import my.work.book.manager.mapper.RequestToEntityMapper;
import my.work.book.manager.repository.BookRepository;
import my.work.book.manager.request.BookRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Service
@Validated
@RequiredArgsConstructor
public class BookService {

    private static final String BOOK_NOT_FOUND_MESSAGE = "Book with id [%d] not found";

    private final BookRepository bookRepository;

    private final RequestToEntityMapper requestToEntityMapper;

    public List<BookEntity> findAll(String category) {
        return Objects.isNull(category)
                ? this.bookRepository.findAll()
                : this.bookRepository.findByCategory(category);
    }

    public BookEntity findById(@Positive(message = "Id must be positive") long id) {
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(BOOK_NOT_FOUND_MESSAGE.formatted(id)));
    }

    public long create(@Valid BookRequest bookRequest) {
        var nextId = this.bookRepository.getNextId();
        var bookEntity = requestToEntityMapper.toEntity(bookRequest, nextId);
        return this.bookRepository.create(bookEntity);
    }

    public void update(
            @Positive(message = "Id must be positive") int id,
            @Valid BookRequest bookRequest) {

        var index = this.bookRepository.getIndex(id);

        if (!bookExists(index)) {
            throw new BookNotFoundException(BOOK_NOT_FOUND_MESSAGE.formatted(id));
        }

        updateBook(index, id, bookRequest);
    }

    public void delete(@Positive(message = "Id must be positive") int id) {
        var deleted = this.bookRepository.delete(id);

        if (!deleted) {
            throw new BookNotFoundException(BOOK_NOT_FOUND_MESSAGE.formatted(id));
        }
    }

    private static boolean bookExists(int index) {
        return (index > 0);
    }

    private void updateBook(int index, int id, BookRequest bookRequest) {
        var updatedBook = this.requestToEntityMapper.toEntity(bookRequest, id);
        this.bookRepository.update(index, updatedBook);
    }

}
