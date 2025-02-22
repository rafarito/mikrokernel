package br.edu.ifba.inf008.shell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf008.interfaces.IUser;

public class User implements IUser, Serializable {
    private static int idCounter = 0;
    private int id;
    private String name;
    private List<Integer> alocatedBooks;

    public User(String name) {
        this.id = idCounter++;
        this.name = name;
        alocatedBooks = new ArrayList<Integer>(5);
    }

    public static void setIdCounter(int idCounter) {
        User.idCounter = idCounter;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public String getUsername() {
        return name;
    }

    public List<Integer> getalocatedBooks(){
        return alocatedBooks;
    }

    @Override
    public void addBook(int reserveId) throws Exception {
        if (alocatedBooks.size() < 5) {
            alocatedBooks.add(reserveId);
        } else {
            throw new Exception("User already has 5 books allocated");
        }
    }
    
    @Override
    public void removeBook(int reserveId) throws Exception {
        if (alocatedBooks.contains(reserveId)) {
            alocatedBooks.remove(reserveId);
        } else {
            throw new Exception("User does not have this book allocated");
        }
    }
}
