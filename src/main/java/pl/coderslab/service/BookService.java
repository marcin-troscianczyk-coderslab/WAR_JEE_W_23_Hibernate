package pl.coderslab.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Category;
import pl.coderslab.repository.BookRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public void update(Book book) {
        bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public Collection<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findAllByTitleUsingQuery(String title) {
        return bookRepository.findAllByTitleUsingQuery(title);
    }

    public Collection<Book> findByCategory(Category category) {
        return bookRepository.findByCategory(category);
    }

    public List<Book> findAllByCategoryUsingQuery(Category category) {
        return bookRepository.findAllByCategoryUsingQuery(category);
    }

    public Page<Book> findFirstByCategoryOrderByTitleUsingQuery(Category category, Pageable pageable) {
        return bookRepository.findFirstByCategoryOrderByTitleUsingQuery(category, pageable);
    }

    /*public List<Book> findAllByRating(int rating) {
        return bookRepository.findAllByRating(rating);
    }*/
}
