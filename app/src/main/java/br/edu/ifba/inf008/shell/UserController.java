package br.edu.ifba.inf008.shell;

import java.util.Map;
import java.util.TreeMap;

import br.edu.ifba.inf008.interfaces.IBook;
import br.edu.ifba.inf008.interfaces.IBookController;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;
import br.edu.ifba.inf008.interfaces.IUser;
import br.edu.ifba.inf008.interfaces.IUserController;

public class UserController implements IUserController{

    private Map<String, IUser> AllUsers;
    private IBookController bookController = ICore.getInstance().getBookController();

    public UserController(){
        AllUsers = new TreeMap<String, IUser>();
    }

    public void registerUser(String name) throws Exception {
        IUser user = new User(name);
        if(AllUsers.containsKey(name)){
            throw new Exception("User already exists");
        }
        AllUsers.put(name, user);
    }

    public void borrowBook(String userName, String title) throws Exception {
        IUser user = AllUsers.get(userName);
        
        if(user == null){
            throw new Exception("User not found");
        }

        bookController.reserveBook(title);
        user.addBook(bookController.reserveBook(title));
    }

    public void returnBook(String userName, int reserveId) throws Exception{
        IUser user = AllUsers.get(userName);
        
        if(user == null){
            throw new Exception("User not found");
        }

        // IBook book = bookController.searchBook(title);
        bookController.unreserveBook(reserveId);
        user.removeBook(reserveId);
    }
    
}
