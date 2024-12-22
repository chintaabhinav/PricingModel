/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Personnel;

import java.util.ArrayList;

import model.Business.Business;

/**
 *
 * @author kal bugrara
 */
public class EmployeeDirectory {

    private Business business;
    private ArrayList<EmployeeProfile> employeeList;

    public EmployeeDirectory(Business business) {
        this.business = business;
        this.employeeList = new ArrayList<>();
    }

    // Creates and adds a new employee profile
    public EmployeeProfile newEmployeeProfile(Person person) {
        EmployeeProfile employeeProfile = new EmployeeProfile(person);
        employeeList.add(employeeProfile);
        return employeeProfile;
    }

    // Finds an employee profile by ID
    public EmployeeProfile findEmployee(String id) {
        for (EmployeeProfile employeeProfile : employeeList) {
            if (employeeProfile.isMatch(id)) {
                return employeeProfile;
            }
        }
        return null; // Not found
    }

    // Getter for the employee list
    public ArrayList<EmployeeProfile> getEmployeeList() {
        return employeeList;
    }
}