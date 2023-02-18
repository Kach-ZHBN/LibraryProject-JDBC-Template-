package by.kach.library.dao;

import by.kach.library.models.Book;
import by.kach.library.models.Person;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("book_id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setYearOfPublish(rs.getInt("year"));
        book.setPersonId(rs.getInt("person_id"));
        return book;
    }

}
