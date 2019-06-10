package com.gmail.a.a.kravchenko;

import java.util.ArrayList;

public class MusicTime extends Shape {
    private ArrayList<String> musicTime = new ArrayList<String>();

    public MusicTime() {
        musicTime.add("Часто");
        musicTime.add("Иногда");
        musicTime.add("Никогда");
    }

    @Override
    public String getValue(int index) {
        return musicTime.get(index);
    }

    @Override
    public void setValue(String musicTime) {
        this.musicTime.add(musicTime);
    }

    @Override
    public int getSize() {
        return musicTime.size();
    }
}
