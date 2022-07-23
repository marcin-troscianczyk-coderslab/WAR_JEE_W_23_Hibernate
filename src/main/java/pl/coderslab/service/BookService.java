package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.dao.BookDao;
import pl.coderslab.entity.Book;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookService {

    private final BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void save(Book book) {
        bookDao.save(book);
    }

    public Book findById(Long id) {
        return bookDao.findById(id);
    }

    public List<Book> findAll() {
        return bookDao.findAll();
    }

    public void update(Book book) {
        bookDao.update(book);
    }

    public void deleteById(Long id) {
        bookDao.deleteById(id);
    }

    public List<Book> findAllByRating(int rating) {
        return bookDao.findAllByRating(rating);
    }
}
