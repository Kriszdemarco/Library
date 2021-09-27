package com.company;

import com.company.item.Item;
import com.company.person.Person;
import com.company.person.Resident;
import com.company.person.Student;


import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Library {
    private Date time;
    private ArrayList<Person> members = new ArrayList<>();
    private ArrayList<Item> store = new ArrayList<>();
    private ArrayList<LibraryBoroweEvent> actualBorowedItems = new ArrayList<>();
    private ArrayList<LibraryBoroweEvent> warningList = new ArrayList<>();
    private ArrayList<LibraryBoroweEvent> boroweHistory=new ArrayList<>();


    public void addToStore(Item item) {
        store.add(item);
    }

    public void addNewMember(Person person) {
        if (!members.contains(person)) {
            person.setRegistred(true);
            members.add(person);
            System.out.println("Member added: " + person.getName());
        } else {
            System.out.println("ERROR: Member already registered: " + person.getName());
        }
    }

    public void boroweItems(Item item, Person person, Date time) {
        if (personCanBrowe(person) && isAvalable(item)) {
            actualBorowedItems.add(new LibraryBoroweEvent(person, item, time));
            store.remove(item);
            person.itemBorowed(item);
            System.out.println("Item borowed by: " + person.getName());
        } else {
            System.out.println("this person reached the browse limit!");
        }
    }

    public int whoIsBrowsedItem(Item item) {
        int target = 0;
        for (int i = 0; i < actualBorowedItems.size(); i++) {
            if (item.getId() == actualBorowedItems.get(i).getItem().getId()) {
                target = actualBorowedItems.get(i).getPerson().getId();
            }
        }
        return target;
    }

    public void returnItem(Person person, Item item) {
        Optional<LibraryBoroweEvent> foundEvent = actualBorowedItems
                .stream()
                .filter(event -> event.getPerson().equals(person) && event.getItem().equals(item))
                .findFirst();
        if(foundEvent.isPresent()) {
            store.add(foundEvent.get().getItem());
            person.itemBringBack(foundEvent.get().getItem());
            boroweHistory.add(foundEvent.get());
            actualBorowedItems.remove(foundEvent.get());
            System.out.println("Item successfully returned!");
        } else {
            System.out.println("ERROR! Item was not returned successfully!");
        }
    }

    //this method return with int number what represent the days betwen the eent and the date
    private int checkTime(LibraryBoroweEvent event, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date firstDate = null;
        Date secondDate = null;
        try {
            firstDate = sdf.parse(String.valueOf(date));
            secondDate = sdf.parse(String.valueOf(event.getDateTime().getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = secondDate.getTime() - firstDate.getTime();
        TimeUnit time = TimeUnit.DAYS;
        long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
        int result = (int) diffrence;
        return result;
    }

    private boolean shoudWarn(LibraryBoroweEvent event, Date date) {
        if (checkTime(event, date) < 7) {
            return false;
        }
        return true;
    }

    private boolean isAvalable(Item item) {
        if (store.contains(item)) {
            return true;
        }
        return false;
    }

    private boolean personCanBrowe(Person person) {
        if (person.isRegistred()) {
            if (person instanceof Student && person.getBorowed().size() < 3) {
                return true;
            } else if (person instanceof Resident && person.getBorowed().size() < 5) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    // list of deleyed borow event
    private void warning(Date time) {
        for (int i = 0; i > actualBorowedItems.size(); i++) {
            if (shoudWarn(actualBorowedItems.get(i), time)) {
                warningList.add(actualBorowedItems.get(i));
            }
        }
    }
    //ha ki tud a vette ki a legtöbb könyvet kene akkor ezt a metodust atirnam
    public void wichBookWasBorowed(){
        System.out.println("This items was borrowed :" );
        for (int i=0; i<boroweHistory.size(); i++){
            System.out.println(boroweHistory.get(i).getItem().getName());
            System.out.println(boroweHistory.get(i).getPerson().getName());
        }
    }

    public void sendwarning(Date date){
        warning(date);
        if(warningList.size()>0){
            for (int i=0; i<warningList.size(); i++){
                System.out.println("piple who late with item: "+ warningList.get(i).getPerson().getName()
                        + warningList.get(i).getItem().getName());
            }
        }else {
            System.out.println("Right now nobody will late with hes /her borrowing");
        }}



}