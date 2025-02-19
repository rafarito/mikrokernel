package br.edu.ifba.inf008.interfaces;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public interface IUIController
{
    public MenuItem createMenuItem(String menuText, String menuItemText);
    public Tab createTab(String tabText, Node contents);
    public Tab setFormScene(String title, Node contents, EventHandler<ActionEvent> submitHandler);
    public Node createForm();
    public Node appendField(Node baseForm, String fieldLabel);
    public void createAlert(String title, String header, String message);
    public void createAlert(String title, String header, String message, AlertType type);
    public void closeTab(Tab tab);
    public Tab listItems(List<List<String>> items);
}
