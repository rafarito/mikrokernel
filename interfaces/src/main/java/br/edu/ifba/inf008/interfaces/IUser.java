package br.edu.ifba.inf008.interfaces;

public interface IUser {
    public void addBook(IBook book) throws Exception;
    public void removeBook(IBook book) throws Exception;
}
