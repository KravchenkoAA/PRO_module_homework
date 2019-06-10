package com.gmail.a.a.kravchenko;

import java.util.ArrayList;

public class Drink extends Shape {
    private ArrayList<String> drinks = new ArrayList<String>();

    public Drink() {
        drinks.add("Чай");
        drinks.add("Кофе");
        drinks.add("Пиво");
    }

    @Override
    public String getValue(int index) {
        return drinks.get(index);
    }

    @Override
    public void setValue(String drink) {
        this.drinks.add(drink);
    }

    @Override
    public int getSize() {
        return drinks.size();
    }
}
