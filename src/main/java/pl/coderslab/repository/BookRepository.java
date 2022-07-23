package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Category;
import pl.coderslab.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);
    List<Book> findByCategory(Category category);
    List<Book> findByCategoryId(long id);


    // zad 3a
    List<Book> findByAuthors(Author author);

    // zad 3b
    List<Book> findByPublisher(Publisher publisher);

    // zad 3c
    List<Book> findByRating(int rating);

    // zad 3d
    Optional<Book> findFirstByCategoryOrderByTitle(Category category);
}
