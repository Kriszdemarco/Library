package com.company.person;

import java.util.ArrayList;
import java.util.Date;

public class Resident extends Person {
    private ArrayList<Date> dates = new ArrayList<>();
    public Resident(int id, String name, boolean isRegistred) {
        super(id, name, isRegistred);
    }

}
