package com.gmail.a.a.kravchenko;

import java.util.HashMap;

public class Anketa {
    private String name;
    private String surname;
    private String age;
    private HashMap<Integer, String> questions = new HashMap<>();

    public Anketa() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public HashMap<Integer, String> getQuestions() {
        return questions;
    }

    public void setQuestions(HashMap<Integer, String> questions) {
        this.questions = questions;
    }

    public String getAnswer(Integer key) {
        return questions.get(key);
    }

    public void setAnswer(Integer key, String value) { questions.put(key, value); }

}
