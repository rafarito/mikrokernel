package br.edu.ifba.inf008.shell;

import java.util.Map;
import java.util.TreeMap;

import br.edu.ifba.inf008.interfaces.IBookController;
import br.edu.ifba.inf008.interfaces.IBook;

public class BookController implements IBookController{
    private Map<String, IBook> Allbooks;

    public BookController(){
        this.Allbooks = new TreeMap<String, IBook>();
    }

    public void registerBook(String title, String author, String isbn, int year, String gender) throws UnsupportedOperationException{
        if(Allbooks.get(title) != null){
            throw new UnsupportedOperationException("Book already registered");
        }

        IBook book = new Book(title, author, year, isbn, gender);
        Allbooks.put(title, book);
    }

    public IBook searchBook(String title) throws Exception {
        IBook book = Allbooks.get(title);

        if(book == null || book.isAvailable()){
            throw new Exception("Book not found");
        }

        return book;
    }

    public void reserveBook(String title) throws Exception {
        IBook book = Allbooks.get(title);

        if(book == null || book.isAvailable()){
            throw new Exception("Book not found");
        }

        book.reserve();
    }

    public void unreserveBook(String title) throws Exception{
        IBook book = Allbooks.get(title);

        if(book == null || book.isAvailable()){
            throw new Exception("Book not found");
        }

        book.unreserve();
    }
}
