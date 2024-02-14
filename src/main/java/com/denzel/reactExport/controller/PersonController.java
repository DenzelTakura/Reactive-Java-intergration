package com.denzel.reactExport.controller;

import com.denzel.reactExport.model.Person;
import com.denzel.reactExport.model.dtos.PersonDto;
import com.denzel.reactExport.service.PersonService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("api/person")
@Data
public class PersonController {
    private final PersonService personService;

    @PostMapping("/add_person")
    public Mono<Person> save(@RequestBody Person person){
        return personService.addPerson(person);
    }

    @GetMapping("/all")
    public Flux<Person> findAll(){
        return personService.findAll();
    }

    @GetMapping("/export")
    public Flux<PersonDto> export(){
        return personService.exportPerson();
    }


    @GetMapping("/find_By_Id{id}")
    public Mono<Person> findById(@PathVariable Long id){
        return personService.findPersonById(id);
    }
}
