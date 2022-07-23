package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.dao.PersonDao;
import pl.coderslab.entity.Person;

import javax.transaction.Transactional;

@Service
@Transactional
public class PersonService {

    private final PersonDao personDao;

    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public void save(Person person) {
        personDao.save(person);
    }

    public Person findById(Long id) {
        return personDao.findById(id);
    }

    public void update(Person person) {
        personDao.update(person);
    }

    public void deleteById(Long id) {
        personDao.deleteById(id);
    }
}
