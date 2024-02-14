package com.denzel.reactExport.service;

import com.denzel.reactExport.model.Person;
import com.denzel.reactExport.model.dtos.PersonDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {
    Mono<Person> addPerson(Person person);

    Flux<Person> findAll();

    Mono<Person> findPersonById(Long id);

    Flux<PersonDto> exportPerson();
}
