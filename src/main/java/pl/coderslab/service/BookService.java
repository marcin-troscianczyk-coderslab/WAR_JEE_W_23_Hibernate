package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Book;
import pl.coderslab.repository.BookRepository;

import javax.transaction.Transactional;
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

    /*public List<Book> findAllByRating(int rating) {
        return bookRepository.findAllByRating(rating);
    }*/
}
