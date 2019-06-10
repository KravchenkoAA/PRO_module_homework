package com.gmail.a.a.kravchenko;

import java.util.HashMap;
import java.util.Map;

public class Database {
    private HashMap<String, Long> database = new HashMap<String, Long>();
    private Drink drinks = new Drink();
    private Color colors = new Color();
    private MusicTime musicTime = new MusicTime();
    private Counter counters = new Counter((drinks.getSize() + colors.getSize() + musicTime.getSize()));

    public Database() {
        for (int i = 0; i < drinks.getSize(); i++) {
            database.put(drinks.getValue(i), 0L);
        }
        for (int i = 0; i < colors.getSize(); i++) {
            database.put(colors.getValue(i), 0L);
        }
        for (int i = 0; i < musicTime.getSize(); i++) {
            database.put(musicTime.getValue(i), 0L);
        }
    }

    public HashMap<String, Long> getDatabase() {
        return database;
    }

    public void setDatabase(HashMap<String, Long> database) {
        this.database = database;
    }

    public String[] toGetCounters() {
        String[] keysDatabase = new String[counters.getSize()];
        int counter = 0;
        for (Map.Entry<String, Long> entry : database.entrySet()) {
            keysDatabase[counter] = entry.getKey();
            counter++;
        }
        return keysDatabase;
    }
}
