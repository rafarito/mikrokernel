package br.edu.ifba.inf008.interfaces;

import java.util.List;

public interface IUser {
    public void addBook(int reserveId) throws Exception;
    public void removeBook(int reserveId) throws Exception;
    public List<Integer> getalocatedBooks();
    public String getUsername();
}
