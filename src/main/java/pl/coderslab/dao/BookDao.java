package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Book book) {
        entityManager.persist(book);
    }

    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
    }

    public List<Book> findAll() {

        Query query = entityManager.createQuery("SELECT b FROM Book b");

        return query.getResultList();
    }

    public void update(Book book) {
        entityManager.merge(book);
    }

    public void deleteById(Long id) {
        Book book = findById(id);
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
    }

    public List<Book> findAllByRating(int rating) {

        Query query = entityManager.createQuery("SELECT b FROM Book b where b.rating = :rating");
        query.setParameter("rating", rating);

        return query.getResultList();
    }

    public List<Book> findAllByPublisherIsNotNull() {
        Query query =
                entityManager.createQuery("SELECT b FROM Book b WHERE b.publisher IS NOT NULL");
        return query.getResultList();
    }

    public List<Book> findAllByPublisher(Publisher publisher) {
        Query query =
                entityManager.createQuery("SELECT b FROM Book b WHERE b.publisher = :publisher");
        query.setParameter("publisher", publisher);
        return query.getResultList();
    }

    public List<Book> findAllByAuthor(Author author) {
        Query query =
                entityManager.createQuery("SELECT b FROM Book b WHERE :author MEMBER OF b.authors");
        query.setParameter("author", author);
        return query.getResultList();
    }

}
