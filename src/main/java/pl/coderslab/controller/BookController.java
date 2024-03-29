package pl.coderslab.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import pl.coderslab.service.AuthorService;
import pl.coderslab.service.BookService;
import pl.coderslab.service.PublisherService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
class BookController {

    private final BookService bookService;
    private final PublisherService publisherService;
    private final AuthorService authorService;

    public BookController(BookService bookService, PublisherService publisherService, AuthorService authorService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.authorService = authorService;
    }

    @PostMapping(path = "/book")
    void save(@RequestParam String title, @RequestParam int rating, @RequestParam String description) {

        Publisher publisher = publisherService.findById(1L);

        List<Author> authors = Arrays.asList(
                authorService.findById(1L),
                authorService.findById(2L)
        );

        Book book = new Book();

        book.setTitle(title);
        book.setRating(rating);
        book.setDescription(description);
        book.setPublisher(publisher);
        book.setAuthors(authors);

        bookService.save(book);
    }

    @GetMapping(path = "/book/{id}")
    String findById(@PathVariable Long id) {

        Optional<Book> book = bookService.findById(id);

        return book.isPresent() ? book.get().toString() : "Nie znaleziono książki o podanym id " + id;
    }

    @GetMapping(path = "/books")
    String findAll() {

        List<Book> books = bookService.findAll();

        return books.toString();
    }

    /*@GetMapping(path = "/books", params = "rating")
    String findAllByRating(@RequestParam int rating) {

        List<Book> books = bookService.findAllByRating(rating);

        return books.toString();
    }*/

    @PutMapping(path = "/book/{id}")
    void update(@PathVariable Long id, @RequestParam String title, @RequestParam int rating, @RequestParam String description) {

        Optional<Book> obook = bookService.findById(id);

        if (obook.isPresent()) {

            Book book = obook.get();

            book.setTitle(title);
            book.setRating(rating);
            book.setDescription(description);

            bookService.update(book);
        }
    }

    @DeleteMapping(path = "/book/{id}")
    void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
