package com.company;

import com.company.item.Book;
import com.company.person.Person;
import com.company.person.Resident;
import com.company.person.Student;

import java.util.Date;

public class Main {
    public static void main(String[] args)throws Exception {
        //peldanyositasok
        Library library =new Library();
        Book bookA = new Book(254,"Lords", 300);
        Book bookB = new Book(255,"behinde the true", 400);
        Book bookC = new Book(256, "the less is more!", 150);
        Person student1=new Student(01,"Joe", true);
        Person resident1=new Resident(02,"Peter", false);
        // feltöltjük a könyvtarat itemekkel!
        library.addToStore(bookA);
        library.addToStore(bookB);
        library.addToStore(bookC);
        //beiratjuk a könyvtarba az uj tagot
        library.addNewMember(resident1);
        library.addNewMember(resident1);
        //kölcsönzes
        library.boroweItems(bookA, resident1, new Date());
        //megnezük ki kölcsönzött a könyvtarbol
        library.whoIsBrowsedItem(bookA);
        //visszaviszük a könyvet!
        library.returnItem(resident1,bookA);
        resident1.printBorowed();
        library.sendwarning(new Date());
        library.wichBookWasBorowed();






    }
}
