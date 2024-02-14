# Reactive-Java-intergration

Java Reactive BackEnd Application that records Personal information of an individual and exports it to an external Application that has requested it.

Below is a code snippet showing the integration of the systems 

''' java
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
'''

 Be sure to replace"  http://192.168.32.66:8081/person/add"  with your own endpoint