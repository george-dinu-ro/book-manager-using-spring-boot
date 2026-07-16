package my.work.book.manager.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record BookRequest(
        @Size(min = 1, max = 50)
        String title,

        @Size(min = 1, max = 50)
        String author,

        @Size(min = 1, max = 50)
        String category,

        @Min(1)
        @Max(5)
        int rating) {
}
