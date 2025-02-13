package br.edu.ifba.inf008.interfaces;

public interface IBookController {
    public void registerBook(String title, String author, String isbn, int year, String gender) throws UnsupportedOperationException;
    public IBook searchBook(String title) throws Exception;
    public void reserveBook(String title) throws Exception;
    public void unreserveBook(String title) throws Exception;
}
