package br.edu.ifba.inf008.interfaces;

import java.util.Date;

public interface IUserController {
    public void registerUser(String name) throws UnsupportedOperationException;
    public void borrowBook(String userName, String title, Date loanDate) throws Exception;
    public void returnBook(String userName, int reserveId) throws Exception;
}
