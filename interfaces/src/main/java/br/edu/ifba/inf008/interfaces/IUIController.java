package br.edu.ifba.inf008.interfaces;

import javafx.scene.control.MenuItem;
import javafx.scene.Node;

public interface IUIController
{
    public MenuItem createMenuItem(String menuText, String menuItemText);
    public boolean createTab(String tabText, Node contents);
    public void setFormScene(String title, Node contents);
    public Node createUserForm();
    public Node appendField(Node baseForm, String fieldLabel);
}
