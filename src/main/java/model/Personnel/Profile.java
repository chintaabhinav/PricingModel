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
public abstract class Profile {

    private Person person; // Made private for encapsulation

    // Constructor initializes Profile with a Person
    public Profile(Person person) {
        this.person = person;
    }

    // Abstract method to be implemented by subclasses to get the role
    public abstract String getRole();

    // Getter for the associated person
    public Person getPerson() {
        return person;
    }

    // Checks if the given ID matches the associated person's ID
    public boolean isMatch(String id) {
        return person.getPersonId().equals(id);
    }
}