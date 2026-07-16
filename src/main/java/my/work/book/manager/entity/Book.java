package my.work.book.manager.entity;

import lombok.Builder;

@Builder
public record Book(long id, String title, String author, String category, int rating) {
}
