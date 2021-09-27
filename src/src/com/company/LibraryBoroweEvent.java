package com.company;
import com.company.item.Item;
import com.company.person.Person;

import java.util.Date;


public class LibraryBoroweEvent {

    private Person person;
    private Item item;
    private Date dateTime;

    public LibraryBoroweEvent(Person person, Item item, Date dateTime) {
        this.person = person;
        this.item = item;
        this.dateTime = dateTime;
    }

    public Person getPerson() {
        return person;
    }

    public Item getItem() {
        return item;
    }

    public Date getDateTime() {
        return dateTime;
    }
}
