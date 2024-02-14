package com.denzel.reactExport.repository;

import com.denzel.reactExport.model.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepo extends ReactiveCrudRepository<Person,Long> {
    Mono<Person> save(Person person);

    Flux<Person> findAll();

    Mono<Person> findPersonById(Long id);
}
