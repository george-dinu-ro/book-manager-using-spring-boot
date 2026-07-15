package my.work.book.manager.entity;

import lombok.Builder;

@Builder
public record Book(String title, String author, String category) {
}
