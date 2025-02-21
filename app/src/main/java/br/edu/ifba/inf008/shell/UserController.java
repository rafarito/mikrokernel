package br.edu.ifba.inf008.shell;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import br.edu.ifba.inf008.interfaces.IBookController;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUser;
import br.edu.ifba.inf008.interfaces.IUserController;

public class UserController implements IUserController, Serializable{

    private Map<String, IUser> AllUsers;
    private IBookController bookController = ICore.getInstance().getBookController();

    public UserController(){
        AllUsers = new TreeMap<String, IUser>();
    }

    public void registerUser(String name) throws UnsupportedOperationException {
        IUser user = new User(name);
        
        if(AllUsers.containsKey(name)){
            throw new UnsupportedOperationException("User already exists");
        }

        if(name == null || name.trim().isEmpty()){
            throw new UnsupportedOperationException("Username must be filled");
        }

        AllUsers.put(name, user);
    }

    public void borrowBook(String userName, String title, Date loanDate) throws Exception {
        IUser user = AllUsers.get(userName);
        
        if(user == null){
            throw new Exception("User not found");
        }

        user.addBook(bookController.reserveBook(title, loanDate));
    }

    public void returnBook(String userName, int reserveId) throws Exception{
        IUser user = AllUsers.get(userName);
        
        if(user == null){
            throw new Exception("User not found");
        }

        bookController.unreserveBook(reserveId);
        user.removeBook(reserveId);
    }
    
}
