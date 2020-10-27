package com.example.demonstration.repository;


import com.example.demonstration.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.loader.Loader.SELECT;

@Repository
    public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    List<Person> findByFirstName(@Param("firstName") String firstName);

    List<Person> findByFirstNameOrLastName(String firstName, String lastName);

   // @Query( "SELECT p FROM Person p WHERE p.firstName LIKE :s% and p.lastName LIKE :s%")
    List<Person> findByFirstNameAndLastName(String firstName, String lastName);

    Person findByAddress(@Param("address") String address);
}
