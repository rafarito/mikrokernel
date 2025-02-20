package br.edu.ifba.inf008.shell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.edu.ifba.inf008.interfaces.IBookController;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IIOController;
import br.edu.ifba.inf008.interfaces.IUserController;

public class IOController implements IIOController
{

    @Override
    public void readAllPreviousData() {
        ICore core = Core.getInstance();
        
        try {
            FileInputStream file = new FileInputStream(new File("program.dat"));
            ObjectInputStream objectStream = new ObjectInputStream(file);
            
            core.setBookController((IBookController) objectStream.readObject());
            core.setUserController((IUserController) objectStream.readObject());
            User.setIdCounter(objectStream.readInt());
            
            objectStream.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeCurrentData() {
        ICore core = Core.getInstance();
        
        try {
            FileOutputStream file = new FileOutputStream(new File("program.dat"));
            ObjectOutputStream objectStream = new ObjectOutputStream(file); 
            
            objectStream.writeObject(core.getBookController());
            objectStream.writeObject(core.getUserController());
            objectStream.writeInt(User.getIdCounter());

            objectStream.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}
