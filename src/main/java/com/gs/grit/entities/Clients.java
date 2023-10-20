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
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "height")
    private String height;
    @Column(name = "weight")
    private String weight;
    @Column(name = "age")
    private String age;
    @Column(name = "sex")
    private String sex;
    @Column(name = "experience_level")
    private String experience_level;
    @Column(name = "fitness_routine")
    private String fitness_routine;
    @Column(name = "current_diet")
    private String current_diet;
    @Column(name = "injuries")
    private String injuries;
    @Column(name = "struggles")
    private String struggles;
    @Column(name = "why_start_journey")
    private String why_start_journey;
    @Column(name = "driven")
    private String driven;
    @Column(name = "other")
    private String other;

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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

    public String getExperience_level() {
        return experience_level;
    }

    public void setExperience_level(String experience_level) {
        this.experience_level = experience_level;
    }

    public String getFitness_routine() {
        return fitness_routine;
    }

    public void setFitness_routine(String fitness_routine) {
        this.fitness_routine = fitness_routine;
    }

    public String getCurrent_diet() {
        return current_diet;
    }

    public void setCurrent_diet(String current_diet) {
        this.current_diet = current_diet;
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

    public String getWhy_start_journey() {
        return why_start_journey;
    }

    public void setWhy_start_journey(String why_start_journey) {
        this.why_start_journey = why_start_journey;
    }

    public String getDriven() {
        return driven;
    }

    public void setDriven(String driven) {
        this.driven = driven;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "client_id=" + client_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", experience_level='" + experience_level + '\'' +
                ", fitness_routine='" + fitness_routine + '\'' +
                ", current_diet='" + current_diet + '\'' +
                ", injuries='" + injuries + '\'' +
                ", struggles='" + struggles + '\'' +
                ", why_start_journey='" + why_start_journey + '\'' +
                ", driven='" + driven + '\'' +
                ", other='" + other + '\'' +
                '}';
    }

    public Clients(Integer client_id, String first_name, String last_name, String email, String phone_number, String height, String weight, String age, String sex, String experience_level, String fitness_routine, String current_diet, String injuries, String struggles, String why_start_journey, String driven, String other) {
        this.client_id = client_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        this.experience_level = experience_level;
        this.fitness_routine = fitness_routine;
        this.current_diet = current_diet;
        this.injuries = injuries;
        this.struggles = struggles;
        this.why_start_journey = why_start_journey;
        this.driven = driven;
        this.other = other;
    }
    public Clients(){
    }
}
