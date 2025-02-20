package br.edu.ifba.inf008.shell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf008.interfaces.IBook;
import br.edu.ifba.inf008.interfaces.IUser;

public class User implements IUser, Serializable {
    private static int idCounter = 0;
    private int id;
    private String name;
    private List<Integer> AlocatedBooks;

    public User(String name) {
        this.id = idCounter++;
        this.name = name;
        AlocatedBooks = new ArrayList<Integer>(5);
    }

    public static void setIdCounter(int idCounter) {
        User.idCounter = idCounter;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    @Override
    public void addBook(int reserveId) throws Exception {
        if (AlocatedBooks.size() < 5) {
            AlocatedBooks.add(reserveId);
        } else {
            throw new Exception("User already has 5 books allocated");
        }
    }
    
    @Override
    public void removeBook(int reserveId) throws Exception {
        if (AlocatedBooks.contains(reserveId)) {
            AlocatedBooks.remove(reserveId);
        } else {
            throw new Exception("User does not have this book allocated");
        }
    }
}
