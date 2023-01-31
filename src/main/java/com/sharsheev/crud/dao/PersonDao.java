package com.sharsheev.crud.dao;

import com.sharsheev.crud.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from person",new BeanPropertyRowMapper<>(Person.class));
    }
    public Person findById(int id) {
        return jdbcTemplate.query("select * from person where id=?", new Object[]{id},new PersonMapper())
                .stream().findAny().orElse(new Person("Default",20,"defaul@gmail.com","Ulan 50"));

    }
    public Optional<Person> findByEmail(String email) {
        return jdbcTemplate.query("select * from person where email=?", new Object[]{email},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void save(Person per) {
        jdbcTemplate.update("insert into person(name, age, email,address) VALUES ( ?,?,?,?)" ,
                per.getName(),per.getAge(),per.getEmail(),per.getAddress());
    }

    public void update(int id,Person person) {
        jdbcTemplate.update("UPDATE person set name=?,age=?,email=?,address=? where id =?",
                person.getName(),person.getAge(),
                person.getEmail(),person.getAddress(),
                person.getId()
        );

    }
    public void delete(int id) {
        jdbcTemplate.update("delete from person where id=?",id);
    }


//    public void create100People() {
//        Random random = new Random();
//        List<Person> people = new ArrayList<>();
//        for (int i = 10; i < 80; i++) {
//           Person person = Person.builder()
//                   .name("Name " + i)
//                   .age(i)
//                   .email("test" + i + "@gmail.com")
//                   .address("Ulan " + i)
//                   .build();
//           people.add(person);
//        }
//
//        for (Person per: people) {
//            save(per);
//        }
//    }
}
