package com.gs.grit.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "clients")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer client_id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "height")
    private String height;

    @Column(name = "weight")
    private String weight;

    @Column(name = "age")
    private String age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "fitnessRoutine")
    private String fitnessRoutine;

    @Column(name = "diet")
    private String diet;

    @Column(name = "injuries")
    private String injuries;

    @Column(name = "struggles")
    private String struggles;

    @Column(name = "whyStart")
    private String whyStart;

    @Column(name = "driven")
    private String driven;

    @Column(name = "gymAccess")
    private String gymAccess;

    @Column(name = "otherInfo")
    private String otherInfo;

    // Getters and setters, constructors, and other methods


    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFitnessRoutine() {
        return fitnessRoutine;
    }

    public void setFitnessRoutine(String fitnessRoutine) {
        this.fitnessRoutine = fitnessRoutine;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getInjuries() {
        return injuries;
    }

    public void setInjuries(String injuries) {
        this.injuries = injuries;
    }

    public String getStruggles() {
        return struggles;
    }

    public void setStruggles(String struggles) {
        this.struggles = struggles;
    }

    public String getWhyStart() {
        return whyStart;
    }

    public void setWhyStart(String whyStart) {
        this.whyStart = whyStart;
    }

    public String getDriven() {
        return driven;
    }

    public void setDriven(String driven) {
        this.driven = driven;
    }

    public String getGymAccess() {
        return gymAccess;
    }

    public void setGymAccess(String gymAccess) {
        this.gymAccess = gymAccess;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    // Default constructor
    public Clients() {
    }

    // Constructor without generated ID
    public Clients(String firstName, String lastName, String height, String weight, String age, String sex, String fitnessRoutine, String diet, String injuries, String struggles, String whyStart, String driven, String gymAccess, String otherInfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        this.fitnessRoutine = fitnessRoutine;
        this.diet = diet;
        this.injuries = injuries;
        this.struggles = struggles;
        this.whyStart = whyStart;
        this.driven = driven;
        this.gymAccess = gymAccess;
        this.otherInfo = otherInfo;
    }

    // Other constructors as needed
}

