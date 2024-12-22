/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Personnel;

/**
 *
 * @author kal bugrara
 */
public class Person {

    private String id; // Made private for encapsulation

    // Constructor to initialize Person with an ID
    public Person(String id) {
        this.id = id;
    }

    // Getter for person ID
    public String getPersonId() {
        return id;
    }

    // Checks if the given ID matches this person's ID
    public boolean isMatch(String id) {
        return this.id.equals(id);
    }

    @Override
    public String toString() {
        return id;
    }
}