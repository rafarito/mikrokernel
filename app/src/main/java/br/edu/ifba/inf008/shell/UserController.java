package br.edu.ifba.inf008.shell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.edu.ifba.inf008.interfaces.IBook;
import br.edu.ifba.inf008.interfaces.IBookController;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUser;
import br.edu.ifba.inf008.interfaces.IUserController;

public class UserController implements IUserController, Serializable{

    private Map<String, IUser> allUsers;
    private IBookController bookController = ICore.getInstance().getBookController();

    public UserController(){
        allUsers = new TreeMap<String, IUser>();
    }

    public void registerUser(String name) throws UnsupportedOperationException {
        IUser user = new User(name);
        
        if(allUsers.containsKey(name)){
            throw new UnsupportedOperationException("User already exists");
        }

        if(name == null || name.trim().isEmpty()){
            throw new UnsupportedOperationException("Username must be filled");
        }

        allUsers.put(name, user);
    }

    public void borrowBook(String userName, String title, Date loanDate) throws Exception {
        IUser user = allUsers.get(userName);
        
        if(user == null){
            throw new Exception("User not found");
        }

        user.addBook(bookController.reserveBook(title, loanDate));
    }

    public void returnBook(String userName, int reserveId) throws Exception{
        IUser user = allUsers.get(userName);
        
        if(user == null){
            throw new Exception("User not found");
        }

        bookController.unreserveBook(reserveId);
        user.removeBook(reserveId);
    }
    
    public List<List<String>> searchLoanByUser(String userName) {
        IUser user = allUsers.get(userName);
        List<List<String>> alocatedBooks = new ArrayList<List<String>>();
        
        if(user == null){
            return alocatedBooks;
        }

        for (int reserveId : user.getalocatedBooks()) {
            IBook book = bookController.searchReservedBook(reserveId);
            List<String> bookInfo = new ArrayList<String>();
            bookInfo.add(user.getUsername());
            bookInfo.add(String.valueOf(reserveId));
            bookInfo.add(book.getTitle());
            bookInfo.add(book.getAuthor());
            bookInfo.add(book.getIsbn());
            bookInfo.add(book.getGender());
            bookInfo.add(String.valueOf(book.getYear()));
            bookInfo.add(book.getLoanDate().toString());
            alocatedBooks.add(bookInfo);
        }

        return alocatedBooks;
    }

    public List<List<String>> listUsers() {
        List<List<String>> users = new ArrayList<List<String>>();
        
        for (IUser user : allUsers.values()) {
            List<String> userInfo = new ArrayList<String>();
            userInfo.add(Integer.toString(user.getId()));
            userInfo.add(user.getUsername());
            users.add(userInfo);
        }

        return users;
    }
}
