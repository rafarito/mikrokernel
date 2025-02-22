package br.edu.ifba.inf008.interfaces;

import java.util.Date;

public interface IBook {
    public Boolean isAvailable();
    public void reserve();
    public void unreserve();
    public String getTitle();
    public String getAuthor();
    public int getYear();
    public String getIsbn();
    public String getGender();
    public Date getLoanDate();
    public void setLoanDate(Date loanDate);
}
