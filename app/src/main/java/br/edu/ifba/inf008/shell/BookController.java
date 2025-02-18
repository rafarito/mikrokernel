package br.edu.ifba.inf008.shell;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import br.edu.ifba.inf008.interfaces.IBookController;
import br.edu.ifba.inf008.interfaces.IBook;

public class BookController implements IBookController{
    private Map<String, IBook> Allbooks;
    private Map<Integer, IBook> reservedBooks;

    public BookController(){
        this.Allbooks = new TreeMap<String, IBook>();
        this.reservedBooks = new HashMap<Integer, IBook>();
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

        if(book == null || !book.isAvailable()){
            throw new Exception("Book not found");
        }

        return book;
    }

    public int reserveBook(String title) throws Exception {
        IBook book = Allbooks.get(title);

        if(book == null || !book.isAvailable()){
            throw new Exception("Book not found");
        }

        Random rand = new Random();
  
        int reserveId = rand.nextInt(1000);

        while(reservedBooks.containsKey(reserveId)){
            reserveId = rand.nextInt(1000);
        }

        reservedBooks.put(reserveId, book);
        book.reserve();

        return reserveId;
    }

    public void unreserveBook(int reserveId) throws Exception{
        IBook book = reservedBooks.get(reserveId);

        if(book == null || !book.isAvailable()){
            throw new Exception("Book not found");
        }

        reservedBooks.remove(reserveId);
        book.unreserve();
    }
}
