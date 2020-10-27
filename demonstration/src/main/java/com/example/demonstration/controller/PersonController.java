package com.example.demonstration.controller;

import com.example.demonstration.exception.ResourceNotFoundException;
import com.example.demonstration.repository.PersonRepository;
import com.example.demonstration.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
    @RequestMapping("")
    public class PersonController {

        @Autowired
        private PersonRepository personRepository;

        @GetMapping("/person")
        public List<Person> getAllPesron() {
            return (List<Person>) personRepository.findAll();
        }

        @GetMapping("/person/{id}")
        public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long Id) throws ResourceNotFoundException
               {
            Person person = personRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + Id));

                   return ResponseEntity.ok().body(person);
        }

    @PutMapping("/person/address={address}")
    public ResponseEntity <Person > updatePersonByAddress(@PathVariable(value = "address") String address,
                                                      @RequestBody Person personDetails) throws ResourceNotFoundException {
      Person person = (Person) personRepository.findByAddress(address);
      person.setId(personDetails.getId());
      person.setAge(personDetails.getAge());
      person.setLastName(personDetails.getLastName());
      person.setFirstName(personDetails.getFirstName());
      final Person updatedPerson = personRepository.save(person);
      return ResponseEntity.ok(updatedPerson);
    }

    @GetMapping("/person/name={name}")
    public ResponseEntity<List<Person>> getPersonLastName(@PathVariable(value = "name") String firstName,@PathVariable(value="name") String lastName)
    {
        return ResponseEntity.ok().body(personRepository.findByFirstNameOrLastName(firstName,lastName));
    }

    @GetMapping("/person/firstandlastname/firstName={firstName}&lastName={lastName}")
    public ResponseEntity<List<Person>> getPersonfirstandLastName(@PathVariable(value = "firstName",required=true) String firstName,@PathVariable(value="lastName",required = true) String lastName)
    {
        return ResponseEntity.ok().body(personRepository.findByFirstNameAndLastName(firstName,lastName));
    }

        @PostMapping("/person")
        public Person createPerson(@RequestBody Person p) {

            return personRepository.save(p);
        }

    }
