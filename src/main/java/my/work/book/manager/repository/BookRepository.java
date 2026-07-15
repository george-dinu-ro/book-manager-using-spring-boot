package my.work.book.manager.repository;

import jakarta.annotation.PostConstruct;
import my.work.book.manager.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookRepository {

    private final List<Book> books = new ArrayList<>();

    @PostConstruct
    private void init() {
        books.addAll(
                List.of(
                        new Book("Clean Code", "Robert C. Martin", "Programming"),
                        new Book("Effective Java", "Joshua Bloch", "Programming"),
                        new Book("Design Patterns", "Erich Gamma", "Software Engineering"),
                        new Book("The Pragmatic Programmer", "Andrew Hunt", "Programming"),
                        new Book("Refactoring", "Martin Fowler", "Software Engineering"),
                        new Book("Atomic Habits", "James Clear", "Personal Development"),
                        new Book("1984", "George Orwell", "Fiction"),
                        new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"),
                        new Book("Sapiens", "Yuval Noah Harari", "History"),
                        new Book("Thinking, Fast and Slow", "Daniel Kahneman", "Psychology")
                ));
    }

}
