package br.edu.ifba.inf008.interfaces;

import java.util.List;

public interface IBookController {
    public void registerBook(String title, String author, String isbn, int year, String gender) throws UnsupportedOperationException;
    public IBook searchBook(String title) throws Exception;
    public int reserveBook(String title) throws Exception;
    public void unreserveBook(int reserveId) throws Exception;
    public List<List<String>> toFieldList();
    public List<List<String>> searchBooksBySubstring(String substring);
}
