package br.edu.ifba.inf008.interfaces;

public abstract class ICore
{
    public static ICore getInstance() {
        return instance;
    }

    public abstract IUIController getUIController();
    public abstract IAuthenticationController getAuthenticationController();
    public abstract IIOController getIOController();
    public abstract IPluginController getPluginController();
    public abstract IBookController getBookController();
    public abstract IUserController getUserController();

    protected static ICore instance = null;

    public abstract void setBookController(IBookController object);
    public abstract void setUserController(IUserController object);
}
