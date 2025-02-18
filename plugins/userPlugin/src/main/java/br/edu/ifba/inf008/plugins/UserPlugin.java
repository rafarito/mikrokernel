package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;

import javafx.scene.control.MenuItem;
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
        uiController.setFormScene("Register", form);
    }

}
