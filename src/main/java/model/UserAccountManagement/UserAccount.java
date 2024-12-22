/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.UserAccountManagement;

import model.Personnel.Profile;

/**
 *
 * @author kal bugrara
 */
public class UserAccount {

    private Profile profile;
    private String username;
    private String password;

    // Constructor initializes the user account with profile, username, and password
    public UserAccount(Profile profile, String username, String password) {
        this.profile = profile;
        this.username = username;
        this.password = password;
    }

    // Returns the associated person's ID
    public String getPersonId() {
        return profile.getPerson().getPersonId();
    }

    // Checks if the given ID matches the associated person's ID
    public boolean isMatch(String id) {
        return getPersonId().equals(id);
    }

    // Validates the user with the given username and password
    public boolean isValidUser(String username, String password) {
        return this.username.equalsIgnoreCase(username) && this.password.equals(password);
    }

    // Returns the role associated with the profile
    public String getRole() {
        return profile.getRole();
    }

    // Returns the associated profile
    public Profile getAssociatedPersonProfile() {
        return profile;
    }
}
