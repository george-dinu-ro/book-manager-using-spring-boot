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
                        new Book(1, "Clean Code", "Robert C. Martin", "Programming", 5),
                        new Book(2, "Effective Java", "Joshua Bloch", "Programming", 4),
                        new Book(3, "Design Patterns", "Erich Gamma", "Software Engineering", 5),
                        new Book(4, "The Pragmatic Programmer", "Andrew Hunt", "Programming", 5),
                        new Book(5, "Refactoring", "Martin Fowler", "Software Engineering", 3),
                        new Book(6, "Atomic Habits", "James Clear", "Personal Development", 2),
                        new Book(7, "1984", "George Orwell", "Fiction", 2),
                        new Book(8, "The Hobbit", "J.R.R. Tolkien", "Fantasy", 4),
                        new Book(9, "Sapiens", "Yuval Noah Harari", "History", 2),
                        new Book(10, "Thinking, Fast and Slow", "Daniel Kahneman", "Psychology", 4)));
    }

    public List<Book> findAll() {
        return this.books;
    }

    public Optional<Book> findById(long id) {
        return this.books.stream()
                .filter(filterById(id))
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

    public int getIndex(int id) {
        return IntStream.range(0, this.books.size())
                .filter(filterByIndex(id))
                .findFirst()
                .orElse(-1);
    }

    public void update(int index, Book book) {
        this.books.set(index, book);
    }

    public boolean delete(String title) {
        return this.books.removeIf(filterByTitle(title));
    }

    private static Predicate<Book> filterByTitle(String title) {
        return book -> book.title().equalsIgnoreCase(title);
    }

    private static Predicate<Book> filterById(long id) {
        return book -> book.id() == id;
    }

    private static Predicate<Book> filterByCategory(String category) {
        return book -> book.category().equalsIgnoreCase(category);
    }

    private IntPredicate filterByIndex(int id) {
        return index -> this.books.get(index).id() == id;
    }

}
