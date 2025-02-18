package br.edu.ifba.inf008.interfaces;

public interface IBookController {
    public void registerBook(String title, String author, String isbn, int year, String gender) throws UnsupportedOperationException;
    public IBook searchBook(String title) throws Exception;
    public int reserveBook(String title) throws Exception;
    public void unreserveBook(int reserveId) throws Exception;
}
