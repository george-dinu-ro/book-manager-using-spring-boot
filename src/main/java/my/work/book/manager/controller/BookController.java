package my.work.book.manager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import my.work.book.manager.entity.BookEntity;
import my.work.book.manager.request.BookRequest;
import my.work.book.manager.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RequestMapping("/api/v1/books")
@RestController
@Validated
@Tag(name = "Books rest api endpoints", description = "Operations related to books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all books", description = "Retrieve a list of all available books")
    ResponseEntity<List<BookEntity>> findAll(
            @RequestParam(required = false)
            @Parameter(description = "Optional query parameter")
            String category) {

        return ResponseEntity.ok(this.bookService.findAll(category));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a books by id", description = "Retrieve a specific book by id")
    ResponseEntity<BookEntity> findById(
            @PathVariable
            @Positive(message = "Id must be positive")
            @Parameter(description = "Id of the book to be retrieved")
            int id) {

        return ResponseEntity.ok(bookService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new book", description = "Add a new book to storage")
    ResponseEntity<BookEntity> create(
            @RequestBody
            @Valid
            BookRequest bookRequest) {

        var id = this.bookService.create(bookRequest);
        return ResponseEntity.created(URI.create("/api/v1/books/%d".formatted(id))).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update a book", description = "Update the details of an existing book")
    ResponseEntity<BookEntity> update(
            @PathVariable
            @Positive(message = "Id must be positive")
            @Parameter(description = "Id of the book to be updated")
            int id,

            @RequestBody
            @Valid
            BookRequest bookRequest) {

        this.bookService.update(id, bookRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a book", description = "Remove a book from storage")
    ResponseEntity<Void> delete(
            @PathVariable
            @Positive(message = "Id must be positive")
            @Parameter(description = "Id of the book to be deleted")
            int id) {

        this.bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
