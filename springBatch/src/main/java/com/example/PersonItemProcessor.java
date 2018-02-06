package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);
    private int i = 0;

    @Override
    public Person process(final Person person) throws Exception {
//        if (person.getFirstName().equals("John")) {
//            if (i == 10) {
//                System.out.println(person);
//            } else {
//                i++;
//                System.out.println(1 / 0);
//            }
//        }
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();

        final Person transformedPerson = new Person(firstName, lastName);

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }

}
