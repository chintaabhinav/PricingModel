/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Personnel;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class PersonDirectory {

    private ArrayList<Person> personList;

    // Constructor initializes the person list
    public PersonDirectory() {
        this.personList = new ArrayList<>();
    }

    // Creates and adds a new person with the given ID
    public Person newPerson(String id) {
        Person person = new Person(id);
        personList.add(person);
        return person;
    }

    // Finds a person by their ID
    public Person findPerson(String id) {
        for (Person person : personList) {
            if (person.isMatch(id)) {
                return person;
            }
        }
        return null; // Not found
    }

    // Getter for the person list
    public ArrayList<Person> getPersonList() {
        return personList;
    }
}
