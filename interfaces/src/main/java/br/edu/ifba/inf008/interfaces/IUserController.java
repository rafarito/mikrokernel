package br.edu.ifba.inf008.interfaces;

public interface IUserController {
    public void registerUser(String name) throws UnsupportedOperationException;
    public void borrowBook(String userName, String title) throws Exception;
    public void returnBook(String userName, int reserveId) throws Exception;
}
