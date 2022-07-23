package pl.coderslab.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Author;
import pl.coderslab.service.AuthorService;

import java.util.List;

@RestController
class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(path = "/author")
    void save(@RequestParam String firstName, @RequestParam String lastName) {

        Author author = new Author();

        author.setFirstName(firstName);
        author.setLastName(lastName);

        authorService.save(author);
    }

    @GetMapping(path = "/author/{id}")
    String findById(@PathVariable Long id) {

        Author author = authorService.findById(id);

        return author != null ? author.toString() : "Nie znaleziono autora o podanym id " + id;
    }

    @GetMapping(path = "/authors")
    String findAll() {

        List<Author> authors = authorService.findAll();

        return authors.toString();
    }

    @PutMapping(path = "/author/{id}")
    void update(@PathVariable Long id, @RequestParam String firstName, @RequestParam String lastName) {

        Author author = authorService.findById(id);

        if (author != null) {
            author.setFirstName(firstName);
            author.setLastName(lastName);

            authorService.update(author);
        }
    }

    @DeleteMapping(path = "/author/{id}")
    void deleteById(@PathVariable Long id) {
        authorService.delete(id);
    }
}
