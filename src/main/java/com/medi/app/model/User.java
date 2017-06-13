package com.medi.app.model;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by vvicario on 23/05/17.
 */
@XmlRootElement(name = "user")
public class User {

    private Integer id;

    @NotNull
    @Size(min = 3, max = 25, message = "The length of name should be between 1 to 25")
    private String name;

    @NotNull
    @NotBlank
    private String surname;

    @NotNull
    @Range(min = 15, max = 80, message = "User shall be minimum of age 15 yr and maximum of age of 80 yr")
    private Integer age;

    @NotNull
    @Pattern(message = "Invalid Email Address->" +
            "Valid emails:user@gmail.com or my.user@domain.com etc.",
            regexp = "^[a-zA-Z0-9_!#$%&�*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    private String gender;

    private String civilStatus;

    public User(){
    }

    public User(String name, String surname, Integer age, String gender, String civilStatus,Integer id) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.civilStatus = civilStatus;
        this.id = id;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
