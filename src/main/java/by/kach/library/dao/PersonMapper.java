package by.kach.library.dao;

import by.kach.library.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RowMapper - объект, который преобразует полученные от БД строки в объекты. Необходим JDBCTemplat'у.
 * PersonMapper - объект имплементирующий RowMapper, который описывает как преобразовать полученную строку в объект.
 */
public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setId(rs.getInt("person_id"));
        person.setName(rs.getString("name"));
        person.setYearOfBirth(rs.getInt("birthday"));
        return person;
    }
}
