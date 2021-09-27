package com.company.person;

import com.company.item.Item;

import java.util.ArrayList;

public class Person {
    private int id;
    private String name;
    private boolean isRegistred = false;
    private ArrayList<Item> borowed = new ArrayList<>();


    public Person(int id, String name, boolean isRegistred) {
        this.id = id;
        this.name = name;
        this.isRegistred=isRegistred;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRegistred() {
        return isRegistred;
    }

    public void setRegistred(boolean registred) {
        this.isRegistred = registred;
    }

    public ArrayList<Item> getBorowed() {
        return borowed;
    }

    public void printBorowed() {
        System.out.println("Borowed items: ");
        borowed.forEach(item -> {
            System.out.println(item);
        });
    }

    public void itemBorowed(Item item) {
        borowed.add(item);
    }

    public void itemBringBack(Item item){
        borowed.remove(item);
    }
}