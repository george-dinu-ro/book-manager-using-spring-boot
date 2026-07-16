package my.work.book.manager.mapper;

import my.work.book.manager.entity.BookEntity;
import my.work.book.manager.request.BookRequest;
import org.springframework.stereotype.Component;

@Component
public class RequestToEntityMapper {

    public BookEntity toEntity(BookRequest bookRequest, long id) {
        return BookEntity.builder()
                .id(id)
                .title(bookRequest.title())
                .author(bookRequest.author())
                .category(bookRequest.category())
                .rating(bookRequest.rating())
                .build();
    }

}
