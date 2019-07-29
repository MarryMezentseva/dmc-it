package com.airplainsoft.it.dto;

import java.util.List;

public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private Integer id;
    private List<String> jobPositions;

    public UserDto(String firstName, String lastName, String email, Integer id, List<String> jobPositions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        this.jobPositions = jobPositions;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getJobPositions() {
        return jobPositions;
    }

    public void setJobPositions(List<String> jobPositions) {
        this.jobPositions = jobPositions;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", jobPositions=" + jobPositions +
                '}';
    }
}




