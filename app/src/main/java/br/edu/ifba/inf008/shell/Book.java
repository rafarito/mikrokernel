package br.edu.ifba.inf008.shell;

import java.io.Serializable;
import java.util.Date;

import br.edu.ifba.inf008.interfaces.IBook;

public class Book implements IBook, Serializable{
    private String title;
    private String author;
    private int year;
    private String isbn;
    private String gender;
    private Date loanDate;
    private Boolean availability = true;

    public Book(String title, String author, int year, String isbn, String gender){
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.gender = gender;
    }

    public void setLoanDate(Date loanDate){
        this.loanDate = loanDate;
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

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGender() {
        return gender;
    }
    public Date getLoanDate(){
        return loanDate;
    }
}
