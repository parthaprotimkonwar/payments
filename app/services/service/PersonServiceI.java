package services.service;

import java.util.List;

import models.Person;

public interface PersonServiceI {

	Person savePerson(Person aPerson);
	
	Person findOnePerson(Long id);
	
	List<Person> persons();
	
}
