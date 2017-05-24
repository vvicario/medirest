package com.medi.app.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by vvicario on 23/05/17.
 */
@XmlRootElement(name = "user")
public class User {

    private String name;
    private String surname;

    public User(){
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
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
}
