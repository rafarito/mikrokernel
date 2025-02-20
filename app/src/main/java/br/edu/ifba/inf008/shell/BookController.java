package br.edu.ifba.inf008.shell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Stream;

import br.edu.ifba.inf008.interfaces.IBookController;
import br.edu.ifba.inf008.interfaces.IBook;

public class BookController implements IBookController, Serializable{
    private Map<String, IBook> allbooks;
    private Map<Integer, IBook> reservedBooks;
    private Map<String, String> registeredIsbns;

    public BookController(){
        this.allbooks = new TreeMap<String, IBook>();
        this.reservedBooks = new HashMap<Integer, IBook>();
        this.registeredIsbns = new HashMap<String, String>();
    }

    public void registerBook(String title, String author, String isbn, int year, String gender) throws UnsupportedOperationException{

        if (Stream.of(title, author, isbn, gender).anyMatch(s -> s == null || s.trim().isEmpty())) {
            throw new UnsupportedOperationException("All fields must be filled");
        }
        if(allbooks.get(title) != null){
            throw new UnsupportedOperationException("Book already registered");
        }
        if (registeredIsbns.get(isbn) != null) {
            throw new UnsupportedOperationException("ISBN already registered");
        }

        registeredIsbns.put(isbn, title);
        IBook book = new Book(title, author, year, isbn, gender);
        allbooks.put(title, book);
    }

    public IBook searchBook(String title) throws Exception {
        IBook book = allbooks.get(title);

        if(book == null || !book.isAvailable()){
            throw new Exception("Book not found");
        }

        return book;
    }

    public int reserveBook(String title) throws Exception {
        IBook book = allbooks.get(title);

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

    public List<List<String>> toFieldList(){
        List<List<String>> books = new ArrayList<List<String>>();

        for(IBook book : allbooks.values()){
            if(!book.isAvailable()){
                continue;
            }
            List<String> bookData = new ArrayList<String>();
            bookData.add(book.getTitle());
            bookData.add(book.getAuthor());
            bookData.add(book.getIsbn());
            bookData.add(Integer.toString(book.getYear()));
            bookData.add(book.getGender());

            books.add(bookData);
        }

        return books;
    }

    public List<List<String>> searchBooksBySubstring(String substring){
        List<List<String>> results = new ArrayList<List<String>>();

        String lowerSub = substring.toLowerCase();
      
        for (Map.Entry<String, IBook> entry : allbooks.entrySet()) {

            if(entry.getKey().toLowerCase().contains(lowerSub) && entry.getValue().isAvailable()){
                List<String> bookData = new ArrayList<String>();
                IBook book = entry.getValue();
                bookData.add(book.getTitle());
                bookData.add(book.getAuthor());
                bookData.add(book.getIsbn());
                bookData.add(Integer.toString(book.getYear()));
                bookData.add(book.getGender());

                results.add(bookData);
            }
        }
      
        return results;
    }
}
