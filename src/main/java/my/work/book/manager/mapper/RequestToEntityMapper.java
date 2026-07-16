package my.work.book.manager.mapper;

import lombok.RequiredArgsConstructor;
import my.work.book.manager.entity.BookEntity;
import my.work.book.manager.repository.BookRepository;
import my.work.book.manager.request.BookRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RequestToEntityMapper {

    private final BookRepository bookRepository;

    public List<BookEntity> toEntities(List<BookRequest> bookRequests) {
        return bookRequests.stream()
                .map(this::toEntity)
                .toList();
    }

    public BookEntity toEntity(BookRequest bookRequest) {
        return BookEntity.builder()
                .id(bookRepository.getNextId())
                .title(bookRequest.title())
                .author(bookRequest.author())
                .category(bookRequest.category())
                .rating(bookRequest.rating())
                .build();
    }

}
