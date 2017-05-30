package com.medi.app.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by vvicario on 23/05/17.
 */
@XmlRootElement(name = "user")
public class User {

    private Integer id;
    private String name;
    private String surname;
    private Integer age;
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
}
