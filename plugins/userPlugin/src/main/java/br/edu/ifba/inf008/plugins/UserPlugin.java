package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class UserPlugin implements IPlugin
{
    public boolean init() {
        IUIController uiController = ICore.getInstance().getUIController();

        MenuItem registerUserItem = uiController.createMenuItem("User", "Register User");
        registerUserItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                registerUser();
            }
        });
        MenuItem borrowBookItem = uiController.createMenuItem("User", "Borrow book");
        borrowBookItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // uiController.createMenuItem("User", "User registered");
            }
        });
        MenuItem returnBookItem = uiController.createMenuItem("User", "Return book");
        returnBookItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // uiController.createMenuItem("User", "User registered");
            }
        });

        // uiController.createTab("new tab", new Rectangle(200,200, Color.LIGHTSTEELBLUE));

        return true;
    }

    private void registerUser() {
        IUIController uiController = ICore.getInstance().getUIController();
        Node form = uiController.createUserForm();
        uiController.appendField(form, "Username");    
        uiController.setFormScene("Register", form, new EventHandler<ActionEvent>() {
        @Override
            public void handle(ActionEvent e) {
                if (form instanceof GridPane) {
                    GridPane grid = (GridPane) form;
                    for (Node node : grid.getChildren()) {
                        if (node instanceof TextField) {
                            TextField textField = (TextField) node;
                            String value = textField.getText();
                            try {
                                ICore.getInstance().getUserController().registerUser(value);
                                uiController.createAlert("User registered", "User registered", "User registered successfully");
                            } catch (UnsupportedOperationException ex) {
                                uiController.createAlert("Error", "One error ocourred",  ex.getMessage(), AlertType.ERROR);
                            }
                        }
                    }
                    // Here you can add further processing of the captured values.
                }
            }
        });
    }
}
