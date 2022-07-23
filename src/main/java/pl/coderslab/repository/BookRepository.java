package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Category;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);
    List<Book> findByCategory(Category category);
    List<Book> findByCategoryId(long id);
}
