package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.*;

public class Core extends ICore
{
    private Core() {}

    public static boolean init() {
        if (instance != null) {
            System.out.println("Fatal error: core is already initialized!");
            System.exit(-1);
        }

        instance = new Core();
        UIController.launch(UIController.class);

        return true;
    }
    public IUIController getUIController() {
        return UIController.getInstance();
    }

    public IIOController getIOController() {
        if(ioController == null) {
            ioController = new IOController();
        }
        return ioController;
    }

    public IPluginController getPluginController() {
        if(pluginController == null) {
            pluginController = new PluginController();
        }
        return pluginController;
    }

    public IBookController getBookController() {
        if(bookController == null) {
            bookController = new BookController();
        }
        return bookController;
    }

    public IUserController getUserController() {
        if(userController == null) {
            userController = new UserController();
        }
        return userController;
    }

    private IIOController ioController;
    private IPluginController pluginController;
    private IBookController bookController;
    private IUserController userController;



    @Override
    public void setBookController(IBookController bookController) {
        this.bookController = bookController;
    }

    @Override
    public void setUserController(IUserController userController) {
        this.userController = userController;
    }
}
