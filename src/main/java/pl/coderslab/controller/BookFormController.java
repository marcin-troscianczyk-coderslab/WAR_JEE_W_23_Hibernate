package pl.coderslab.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Category;
import pl.coderslab.entity.Publisher;
import pl.coderslab.service.AuthorService;
import pl.coderslab.service.BookService;
import pl.coderslab.service.CategoryService;
import pl.coderslab.service.PublisherService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
class BookFormController {

    private final BookService bookService;
    private final PublisherService publisherService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookFormController(BookService bookService, PublisherService publisherService, AuthorService authorService, CategoryService categoryService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("publishers")
    Collection<Publisher> findAllPublishers() {
        return publisherService.findAll();
    }

    @ModelAttribute("authors")
    Collection<Author> findAllAuthors() {
        return authorService.findAll();
    }

    @ModelAttribute("categories")
    Collection<Category> findAllCategories() {
        return categoryService.findAll();
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

    // Book's searcher

    @GetMapping(path = "/book/search")
    String showSearchForm() {
        return "book/search";
    }

    @GetMapping(path = "/book/search", params = "title")
    String searchBookByTitle(@RequestParam String title, Model model) {

        //Collection<Book> books = bookService.findByTitle(title);
        List<Book> books = bookService.findAllByTitleUsingQuery(title);
        model.addAttribute("books", books);

        return "book/list";
    }

    @GetMapping(path = "/book/search", params = "id")
    String searchBookByCategory(Category category, Model model) {

        //Collection<Book> books = bookService.findByCategory(category);
        //List<Book> books = bookService.findAllByCategoryUsingQuery(category);
        Pageable pageable = PageRequest.of(0, 1, Sort.by("title").ascending());
        Page<Book> page = bookService.findFirstByCategoryOrderByTitleUsingQuery(category, pageable);
        model.addAttribute("books", page.get().collect(Collectors.toList()));

        return "book/list";
    }
}
