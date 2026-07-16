package my.work.book.manager.repository;

import jakarta.annotation.PostConstruct;
import my.work.book.manager.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

@Component
public class BookRepository {

    private final List<Book> books = new ArrayList<>();

    @PostConstruct
    public void init() {
        this.books.addAll(
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

    public List<Book> findAll() {
        return this.books;
    }

    public Optional<Book> findByTitle(String title) {
        return this.books.stream()
                .filter(filterByTitle(title))
                .findFirst();
    }

    public List<Book> findByCategory(String category) {
        return this.books.stream()
                .filter(filterByCategory(category))
                .toList();
    }

    public Book create(Book book) {
        this.books.add(book);
        return book;
    }

    public int getIndex(String title) {
        return IntStream.range(0, this.books.size())
                .filter(filterByIndex(title))
                .findFirst()
                .orElse(-1);
    }

    public void update(int index, Book book) {
        this.books.set(index, book);
    }

    private static Predicate<Book> filterByTitle(String title) {
        return book -> book.title().equalsIgnoreCase(title);
    }

    private static Predicate<Book> filterByCategory(String category) {
        return book -> book.category().equalsIgnoreCase(category);
    }

    private IntPredicate filterByIndex(String title) {
        return index -> this.books.get(index).title().equalsIgnoreCase(title);
    }

}
