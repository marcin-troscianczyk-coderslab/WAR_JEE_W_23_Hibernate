package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.entity.Author;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthorService {

    private final AuthorDao authorDao;

    public AuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public void save(Author author) {
        authorDao.save(author);
    }

    public Author findById(Long authorId) {
        return authorDao.findById(authorId);
    }

    public void update(Author author) {
        authorDao.update(author);
    }

    public void delete(Long authorId) {
        authorDao.delete(authorId);
    }
}
