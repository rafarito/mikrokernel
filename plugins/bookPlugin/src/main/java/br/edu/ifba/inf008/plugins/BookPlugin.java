package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class BookPlugin implements IPlugin
{
    public boolean init() {
        IUIController uiController = ICore.getInstance().getUIController();

        MenuItem registerBookItem = uiController.createMenuItem("Book", "Register Book");
        registerBookItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                registerBook();
            }
        });
        MenuItem searchBookItem = uiController.createMenuItem("Book", "Search book");
        searchBookItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                searchBook();
            }
        });
        MenuItem listBookItem = uiController.createMenuItem("Book", "List books");
        listBookItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                listBooks();
            }
        });
        MenuItem returnBookItem = uiController.createMenuItem("Book", "Return book");
        returnBookItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // uiController.createMenuItem("Book", "Book registered");
            }
        });

        return true;
    }

    private void registerBook(){
        IUIController uiController = ICore.getInstance().getUIController();
        Node form = uiController.createForm();
        uiController.appendField(form, "ISBN"); 
        uiController.appendField(form, "Title"); 
        uiController.appendField(form, "Author"); 
        uiController.appendField(form, "Year"); 
        uiController.appendField(form, "gender"); 
        uiController.setFormScene("Register Book", form, new EventHandler<ActionEvent>() {
        @Override
            public void handle(ActionEvent e) {
                if (form instanceof GridPane) {
                    GridPane grid = (GridPane) form;
                    int i = 0;
                    String[] values = new String[5];

                    for (Node node : grid.getChildren()) {
                        if (node instanceof TextField) {
                            TextField textField = (TextField) node;
                            values[i++] = textField.getText();
                        }
                    }

                    try {
                        ICore.getInstance().getBookController().registerBook(values[1], values[2], values[0], Integer.parseInt(values[3]), values[4]);
                        uiController.createAlert("Book registered", "Book registered", "Book registered successfully");
                    } catch (UnsupportedOperationException ex) {
                        uiController.createAlert("Error", "One error ocourred", ex.getMessage(), AlertType.ERROR);
                    } catch (NumberFormatException ex) {
                        uiController.createAlert("Error", "One error ocourred", "Year must be a number", AlertType.ERROR);
                    } catch (Exception ex) {
                        uiController.createAlert("Error", "One error ocourred", ex.getMessage(), AlertType.ERROR);
                    }
                }
            }
        });
    }

    private void searchBook(){
        IUIController uiController = ICore.getInstance().getUIController();
        Node form = uiController.createForm();
        uiController.appendField(form, "Title"); 
        uiController.setFormScene("Search Book", form, new EventHandler<ActionEvent>() {
        @Override
            public void handle(ActionEvent e) {
                if (form instanceof GridPane) {
                    GridPane grid = (GridPane) form;
                    String title = null;

                    for (Node node : grid.getChildren()) {
                        if (node instanceof TextField) {
                            TextField textField = (TextField) node;
                            title = textField.getText();
                        }
                    }

                    try {
                        ICore.getInstance().getBookController().searchBook(title);
                        uiController.createAlert("Book found", "Book found", "Book found successfully");
                    } catch (Exception ex) {
                        uiController.createAlert("Error", "One error ocourred", ex.getMessage(), AlertType.ERROR);
                    }
                }
            }
        });
    }

    private void listBooks(){
        IUIController uiController = ICore.getInstance().getUIController();
        List<List<String>> books = ICore.getInstance().getBookController().toFieldList();
        List<String> columnNames = new ArrayList<String>();
        columnNames.add("Title");
        columnNames.add("Author");
        columnNames.add("ISBN");
        columnNames.add("Year");
        columnNames.add("Gender");
        uiController.listItems(books, columnNames);
    }
}
