package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.*;
import pl.coderslab.service.AuthorService;
import pl.coderslab.service.BookService;
import pl.coderslab.service.PersonService;
import pl.coderslab.service.PublisherService;

import java.util.Arrays;
import java.util.List;

@Controller
class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "/person")
    String showRegistrationForm() {
        return "alamakota";
    }

    @PostMapping(path = "/person")
    //void save(@RequestParam String login, @RequestParam String password, @RequestParam String email) {
    String save(Person person) {

        /*Person person = new Person();

        person.setLogin(login);
        person.setPassword(password);
        person.setEmail(email);*/

        PersonDetails personDetails = new PersonDetails();
        personDetails.setFirstName("Jan");
        personDetails.setLastName("Kowalski");
        personDetails.setCity("Warszawa");

        person.setPersonDetails(personDetails);

        personService.save(person);

        return "success";
    }

    @GetMapping(path = "/person/{id}")
    @ResponseBody
    String findById(@PathVariable Long id) {

        Person person = personService.findById(id);

        return person != null ? person.toString() : "Nie znaleziono osoby o podanym id " + id;
    }

    @PutMapping(path = "/person/{id}")
    @ResponseBody
    void update(@PathVariable Long id, @RequestParam String login, @RequestParam String password, @RequestParam String email) {

        Person person = personService.findById(id);

        if (person != null) {
            person.setLogin(login);
            person.setPassword(password);
            person.setEmail(email);

            personService.update(person);
        }
    }

    @DeleteMapping(path = "/person/{id}")
    @ResponseBody
    void deleteById(@PathVariable Long id) {
        personService.deleteById(id);
    }
}
