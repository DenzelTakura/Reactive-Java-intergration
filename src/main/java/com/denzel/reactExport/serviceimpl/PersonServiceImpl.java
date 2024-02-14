package com.denzel.reactExport.serviceimpl;

import com.denzel.reactExport.model.Person;
import com.denzel.reactExport.model.dtos.PersonDto;
import com.denzel.reactExport.repository.PersonRepo;
import com.denzel.reactExport.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepo personRepo;
    private final WebClient webClient;

    public PersonServiceImpl(PersonRepo personRepo) {
        this.personRepo = personRepo;
        this.webClient = WebClient.create();
    }

    @Override
    public Mono<Person> addPerson(Person person) {
        return personRepo.save(person);
    }

    @Override
    public Flux<Person> findAll() {
        return personRepo.findAll();
    }

    @Override
    public Mono<Person> findPersonById(Long id) {
        return personRepo.findPersonById(id);
    }

    @Override
    public Flux<PersonDto> exportPerson() {
        Flux<Person> personFlux = personRepo.findAll();

        Flux<PersonDto> toPostFlux = personFlux.flatMap(person -> {
            PersonDto personDto = new PersonDto();
            personDto.setFirstName(person.firstName());
            personDto.setLastName(person.lastName());
            personDto.setIdNumber(person.idNumber());

            return webClient.post()
                    .uri("http://192.168.32.66:8081/person/add")
                    .body(BodyInserters.fromValue(personDto))
                    .retrieve()
                    .bodyToFlux(PersonDto.class);
        });
        toPostFlux.subscribe();

        return toPostFlux;
    }


}