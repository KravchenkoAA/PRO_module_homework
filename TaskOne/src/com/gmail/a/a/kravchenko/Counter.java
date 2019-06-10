package com.gmail.a.a.kravchenko;

import java.util.ArrayList;

public class Counter {
    private ArrayList<Long> counters = new ArrayList<Long>();
    private int size;

    public Counter(int size) {
        super();
        this.size = size;
        for (int i = 0; i < size; i++) {
            this.counters.add(i, 0L);
        }
    }

    public int getSize() {
        return this.size;
    }

}
