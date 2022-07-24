package pl.coderslab.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Category;
import pl.coderslab.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);

    @Query("select b from Book b where b.title = :title")
    List<Book> findAllByTitleUsingQuery(@Param("title") String title);

    List<Book> findByCategory(Category category);

    @Query("select b from Book b where b.category = :category")
    List<Book> findAllByCategoryUsingQuery(@Param("category") Category category);

    List<Book> findByCategoryId(long id);

    // zad 3a
    List<Book> findByAuthors(Author author);

    // zad 3b
    List<Book> findByPublisher(Publisher publisher);

    @Query("select b from Book b where b.publisher = :publisher")
    List<Book> findByPublisherUsingQuery(@Param("publisher") Publisher publisher);

    // zad 3c
    List<Book> findByRating(int rating);

    // zad 3d
    Optional<Book> findFirstByCategoryOrderByTitle(Category category);

    @Query("select b from Book b where b.category = :category")
    //@Query(value = "select * from books b where b.category_id = :category_id order by b.title limit 1", nativeQuery = true)
    Page<Book> findFirstByCategoryOrderByTitleUsingQuery(@Param("category") Category category, Pageable pageable);

    @Query("select b from Book b where b.rating between :min and :max")
    List<Book> findAllByRatingBetween(@Param("min") int start, @Param("max") int stop);
}
