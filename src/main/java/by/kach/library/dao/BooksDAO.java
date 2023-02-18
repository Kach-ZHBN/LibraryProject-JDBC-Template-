package by.kach.library.dao;

import by.kach.library.models.Book;
import by.kach.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BooksDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM book ", new BookMapper());
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?", new Object[]{id}, new BookMapper())
                .stream().findAny().orElse(null);
    }

    public void create(Book book){
        jdbcTemplate.update("INSERT INTO book(person_id, name, author, year) VALUES(NULL,?,?,?)",
                book.getName(), book.getAuthor(), book.getYearOfPublish());
    }

    public void update(Book book){
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE book_id=?", book.getName(),
                book.getAuthor(), book.getYearOfPublish(), book.getId());
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public List<Person> getPeople(){
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public void setOwner(Book book){
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", book.getPersonId(), book.getId());
    }
    public void freeOwner(Book book){
        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE book_id=?", book.getId());
    }
}
