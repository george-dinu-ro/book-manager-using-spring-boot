package my.work.book.manager.request;

import lombok.Builder;

@Builder
public record BookRequest(String title, String author, String category, int rating) {
}
