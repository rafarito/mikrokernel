package br.edu.ifba.inf008.shell;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf008.interfaces.IBook;
import br.edu.ifba.inf008.interfaces.IUser;

public class User implements IUser {
    private static int idCounter = 0;
    private int id;
    private String name;
    private List<IBook> AlocatedBooks;

    public User(String name) {
        this.id = idCounter++;
        this.name = name;
        AlocatedBooks = new ArrayList<IBook>(5);
    }

    @Override
    public void addBook(IBook book) throws Exception {
        if (AlocatedBooks.size() < 5) {
            AlocatedBooks.add(book);
        } else {
            throw new Exception("User already has 5 books allocated");
        }
    }
    
    @Override
    public void removeBook(IBook book) throws Exception {
        if (AlocatedBooks.contains(book)) {
            AlocatedBooks.remove(book);
        } else {
            throw new Exception("User does not have this book allocated");
        }
    }
}
