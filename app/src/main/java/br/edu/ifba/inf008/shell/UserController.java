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

    public void registerUser(String name) {
        IUser user = new User(name);
        AllUsers.put(name, user);
    }

    public void borrowBook(String userName, String title) {
        IUser user = AllUsers.get(userName);
        
        if(user == null){
            System.out.println("User not found");
            return;
        }

        try{
            IBook book = bookController.searchBook(title);
            bookController.reserveBook(title);
            user.addBook(book);
        }catch(Exception e){
            System.out.println(e.getMessage());    
        }
    }

    public void returnBook(String userName, String title) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'returnBook'");
    }
    
}
