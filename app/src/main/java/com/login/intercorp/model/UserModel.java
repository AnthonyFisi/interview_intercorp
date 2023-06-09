package com.login.intercorp.model;

import java.io.Serializable;

public class UserModel implements Serializable {

    private String id;
    private String name;
    private String lastName;
    private int age;
    private String birthdate;

    public UserModel(String id,String name, String lastName, int age, String birthdate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.birthdate = birthdate;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
