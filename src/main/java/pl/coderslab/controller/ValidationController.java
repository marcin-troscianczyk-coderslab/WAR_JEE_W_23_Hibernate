package pl.coderslab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Controller
class ValidationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationController.class);

    private Validator validator;

    public ValidationController(Validator validator) {
        this.validator = validator;
    }

    @GetMapping(path = "/validate")
    @ResponseBody
    String validate() {

        Book book = new Book();

        Set<ConstraintViolation<Book>> result = validator.validate(book);

        if (!result.isEmpty()) {

            for (ConstraintViolation<Book> violation : result) {
                LOGGER.error(violation.getPropertyPath() + " " + violation.getMessage());
            }

        } else {
            LOGGER.info("Walidacja zakończona - wszystko ok.");
        }

        return "Walidacja zakończona";
    }

    @GetMapping(path = "/validate-book")
    String validateBook(Book book, Model model) {

        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        model.addAttribute("violations", violations);

        return "validator/result";
    }

    @GetMapping(path = "/validate-author")
    String validateAuthor(Author author, Model model) {

        Set<ConstraintViolation<Author>> violations = validator.validate(author);
        model.addAttribute("violations", violations);

        return "validator/result";
    }

    @GetMapping(path = "/validate-publisher")
    String validatePublisher(Publisher publisher, Model model) {

        Set<ConstraintViolation<Publisher>> violations = validator.validate(publisher);
        model.addAttribute("violations", violations);

        return "validator/result";
    }

}
