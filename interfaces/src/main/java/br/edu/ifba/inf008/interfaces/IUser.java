package br.edu.ifba.inf008.interfaces;

public interface IUser {
    public void addBook(int reserveId) throws Exception;
    public void removeBook(int reserveId) throws Exception;
}
