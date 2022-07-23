package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import pl.coderslab.service.AuthorService;
import pl.coderslab.service.BookService;
import pl.coderslab.service.PublisherService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
class BookFormController {

    private final BookService bookService;
    private final PublisherService publisherService;
    private final AuthorService authorService;

    public BookFormController(BookService bookService, PublisherService publisherService, AuthorService authorService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.authorService = authorService;
    }

    @ModelAttribute("publishers")
    Collection<Publisher> findAllPublishers() {
        return publisherService.findAll();
    }

    @ModelAttribute("authors")
    Collection<Author> findAllAuthors() {
        return authorService.findAll();
    }

    @GetMapping(path = "/book/add")
    String showAddBookForm(Model model) {

        Book book = new Book();
        model.addAttribute("book", book);

        return "book/add";
    }

    @PostMapping(path = "/book/add")
    String processAddBookForm(@Valid Book book, BindingResult result) {

        if (result.hasErrors()) {
            return "book/add";
        }

        bookService.save(book);

        return "redirect:/book/list";
    }

    @GetMapping(path = "/book/list")
    String showBookList(Model model) {

        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);

        return "book/list";
    }

    @GetMapping(path = "book/edit", produces = "text/html;charset=UTF-8")
    String showEditForm(@RequestParam("id") long bookId, Model model) {
        Optional<Book> book = bookService.findById(bookId);
        model.addAttribute("book", book);
        return "book/edit";
    }

    @PostMapping(path = "book/edit", produces = "text/html;charset=UTF-8")
    String processEditForm(Book book) {
        bookService.update(book);
        return "redirect:/book/list";
    }

    @GetMapping(path = "book/remove", produces = "text/html;charset=UTF-8")
    String showRemovingConfirmation(Book book) {
        bookService.deleteById(book.getId());
        return "redirect:/book/list";
    }
}
