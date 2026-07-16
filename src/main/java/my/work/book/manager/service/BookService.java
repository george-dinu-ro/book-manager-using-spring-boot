package my.work.book.manager.service;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import my.work.book.manager.entity.BookEntity;
import my.work.book.manager.mapper.RequestToEntityMapper;
import my.work.book.manager.repository.BookRepository;
import my.work.book.manager.request.BookRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final RequestToEntityMapper requestToEntityMapper;

    public List<BookEntity> findAll(String category) {
        return Objects.isNull(category)
                ? this.bookRepository.findAll()
                : this.bookRepository.findByCategory(category);
    }

    public Optional<BookEntity> findById(@Positive long id) {
        return this.bookRepository.findById(id);
    }

    public long create(BookRequest bookRequest) {
        var nextId = this.bookRepository.getNextId();
        var bookEntity = requestToEntityMapper.toEntity(bookRequest, nextId);
        return this.bookRepository.create(bookEntity);
    }

    public BookEntity update(@Positive int id, BookRequest bookRequest) {
        var index = this.bookRepository.getIndex(id);
        return bookExists(index)
                ? updateAndGet(index, id, bookRequest)
                : null;
    }

    public boolean delete(@Positive int id) {
        return this.bookRepository.delete(id);
    }

    private static boolean bookExists(int index) {
        return (index > 0);
    }

    private BookEntity updateAndGet(int index, int id, BookRequest bookRequest) {
        var updatedBook = this.requestToEntityMapper.toEntity(bookRequest, id);
        this.bookRepository.update(index, updatedBook);
        return updatedBook;
    }

}
