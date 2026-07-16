package my.work.book.manager.entity;

import lombok.Builder;

@Builder
public record BookEntity(long id, String title, String author, String category, int rating) {
}
