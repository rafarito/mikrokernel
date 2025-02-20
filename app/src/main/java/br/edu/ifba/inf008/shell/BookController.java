package br.edu.ifba.inf008.shell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import br.edu.ifba.inf008.interfaces.IBookController;
import br.edu.ifba.inf008.interfaces.IBook;

public class BookController implements IBookController{
    private Map<String, IBook> allbooks;
    private Map<Integer, IBook> reservedBooks;

    public BookController(){
        this.allbooks = new TreeMap<String, IBook>();
        this.reservedBooks = new HashMap<Integer, IBook>();
    }

    public void registerBook(String title, String author, String isbn, int year, String gender) throws UnsupportedOperationException{
        if(allbooks.get(title) != null){
            throw new UnsupportedOperationException("Book already registered");
        }

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
