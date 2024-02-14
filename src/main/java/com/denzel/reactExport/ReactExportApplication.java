package com.denzel.reactExport;

import com.denzel.reactExport.model.Person;
import com.denzel.reactExport.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReactExportApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactExportApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(PersonService personService) {
		return args -> {
			for (int i = 200; i < 400; i++) {
				Person person = new Person(null, "Denzel " + i, "Dzingiso "+i, "12-" + (100000 + i) + "y27");
				personService.addPerson(person).subscribe();

			}
		};
	}

}
