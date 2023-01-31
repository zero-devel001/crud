package com.sharsheev.crud.dao;

import com.sharsheev.crud.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person,Integer> {
    Optional<Person> findById(Long id);
}
