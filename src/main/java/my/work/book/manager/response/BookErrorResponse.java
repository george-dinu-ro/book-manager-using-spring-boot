package my.work.book.manager.response;

import lombok.Builder;

@Builder
public record BookErrorResponse(int status, String message, long timestamp) {
}
