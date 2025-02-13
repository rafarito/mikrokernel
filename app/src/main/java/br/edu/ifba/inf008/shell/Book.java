package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.IBook;

public class Book implements IBook{
    private String title;
    private String author;
    private int year;
    private String isbn;
    private String gender;
    private Boolean availability = true;

    public Book(String title, String author, int year, String isbn, String gender){
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.gender = gender;
    }

    public Boolean isAvailable(){
        return this.availability;
    }

    public void reserve(){
        this.availability = false;
    }

    public void unreserve(){
        this.availability = true;
    }
}
