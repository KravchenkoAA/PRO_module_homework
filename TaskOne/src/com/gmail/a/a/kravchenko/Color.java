package com.gmail.a.a.kravchenko;

import java.util.ArrayList;

public class Color extends Shape {
    private ArrayList<String> colors = new ArrayList<String>();

    public Color() {
        colors.add("Зеленый");
        colors.add("Красный");
        colors.add("Голубой");
    }

    @Override
    public String getValue(int index) {
        return colors.get(index);
    }

    @Override
    public void setValue(String drink) {
        this.colors.add(drink);
    }

    @Override
    public int getSize() {
        return colors.size();
    }
}
