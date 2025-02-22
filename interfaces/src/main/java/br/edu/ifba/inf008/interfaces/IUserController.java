package br.edu.ifba.inf008.interfaces;

import java.util.Date;
import java.util.List;

public interface IUserController {
    public void registerUser(String name) throws UnsupportedOperationException;
    public void borrowBook(String userName, String title, Date loanDate) throws Exception;
    public void returnBook(String userName, int reserveId) throws Exception;
    public List<List<String>> searchLoanByUser(String userName);
}
