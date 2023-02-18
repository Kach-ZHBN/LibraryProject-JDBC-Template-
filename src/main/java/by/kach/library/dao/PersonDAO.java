package by.kach.library.dao;

import by.kach.library.models.Book;
import by.kach.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Data Access Object (DAO) - объект, в который вынесена логика общения с БД
 */
@Component
public class PersonDAO {

    //Bean JdbcTemplate создается в файле-конфигурации Spring-приложения.
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null); //Какая-то магия в виде лямбда-выражения.
        //new Object[]{id} - массив значений, которые передаются в PreparedStatement
    }

    public void save (Person person){
        jdbcTemplate.update("INSERT INTO Person(name, birthday) VALUES(?, ?)", person.getName(), person.getYearOfBirth());
    }

    public void update (Person person){
        jdbcTemplate.update("UPDATE Person SET name=?, birthday=? WHERE person_id=?",
                person.getName(), person.getYearOfBirth(), person.getId());
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }

    public List<Book> getPersonsBooks(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?", new Object[]{id}, new BookMapper());
    }
}
