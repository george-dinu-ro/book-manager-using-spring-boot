package my.work.book.manager.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record BookRequest(
        @NotBlank(message = "Title must be not blank")
        @Size(min = 1, max = 50, message = "Title must have between 1 and 50 characters")
        String title,

        @NotBlank(message = "Author must be not blank")
        @Size(min = 1, max = 50, message = "Author must have between 1 and 50 characters")
        String author,

        @NotBlank(message = "Category must be not blank")
        @Size(min = 1, max = 50, message = "Category must have between 1 and 50 characters")
        String category,

        @Min(value = 1, message = "Rating must be minimum 1")
        @Max(value = 5, message = "Rating must be maximum 5")
        int rating) {
}
