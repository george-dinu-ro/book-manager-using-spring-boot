package my.work.book.manager.repository;

import jakarta.annotation.PostConstruct;
import my.work.book.manager.entity.BookEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

@Component
public class BookRepository {

    private final List<BookEntity> bookEntities = new ArrayList<>();

    @PostConstruct
    public void init() {
        this.bookEntities.addAll(
                List.of(
                        new BookEntity(1, "Clean Code", "Robert C. Martin", "Programming", 5),
                        new BookEntity(2, "Effective Java", "Joshua Bloch", "Programming", 4),
                        new BookEntity(3, "Design Patterns", "Erich Gamma", "Software Engineering", 5),
                        new BookEntity(4, "The Pragmatic Programmer", "Andrew Hunt", "Programming", 5),
                        new BookEntity(5, "Refactoring", "Martin Fowler", "Software Engineering", 3),
                        new BookEntity(6, "Atomic Habits", "James Clear", "Personal Development", 2),
                        new BookEntity(7, "1984", "George Orwell", "Fiction", 2),
                        new BookEntity(8, "The Hobbit", "J.R.R. Tolkien", "Fantasy", 4),
                        new BookEntity(9, "Sapiens", "Yuval Noah Harari", "History", 2),
                        new BookEntity(10, "Thinking, Fast and Slow", "Daniel Kahneman", "Psychology", 4)));
    }

    public List<BookEntity> findAll() {
        return this.bookEntities;
    }

    public Optional<BookEntity> findById(long id) {
        return this.bookEntities.stream()
                .filter(filterById(id))
                .findFirst();
    }

    public List<BookEntity> findByCategory(String category) {
        return this.bookEntities.stream()
                .filter(filterByCategory(category))
                .toList();
    }

    public long create(BookEntity bookEntity) {
        this.bookEntities.add(bookEntity);
        return bookEntity.id();
    }

    public int getIndex(int id) {
        return IntStream.range(0, this.bookEntities.size())
                .filter(filterByIndex(id))
                .findFirst()
                .orElse(-1);
    }

    public void update(int index, BookEntity bookEntity) {
        this.bookEntities.set(index, bookEntity);
    }

    public boolean delete(int id) {
        return this.bookEntities.removeIf(filterById(id));
    }

    public long getNextId() {
        return !this.bookEntities.isEmpty()
                ? (this.bookEntities.getLast().id() + 1)
                : 1;
    }

    private static Predicate<BookEntity> filterById(long id) {
        return bookEntity -> bookEntity.id() == id;
    }

    private static Predicate<BookEntity> filterByCategory(String category) {
        return bookEntity -> bookEntity.category().equalsIgnoreCase(category);
    }

    private IntPredicate filterByIndex(int id) {
        return index -> this.bookEntities.get(index).id() == id;
    }

}
