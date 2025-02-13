package br.edu.ifba.inf008.interfaces;

public interface IUserController {
    public void registerUser(String name);
    public void borrowBook(String userName, String title) throws Exception;
    public void returnBook(String userName, String title) throws Exception;
}
