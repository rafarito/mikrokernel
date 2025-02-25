package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;
import javafx.scene.control.MenuItem;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ReservedBooksPluginReport implements IPlugin
{
    public boolean init() {
        IUIController uiController = ICore.getInstance().getUIController();

        MenuItem listReserved = uiController.createMenuItem("Report", "List reserved books");
        listReserved.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                listReservedBooks();
            }
        });

        return true;
    }

    private void listReservedBooks() {
        IUIController uiController = ICore.getInstance().getUIController();
        List<List<String>> books = ICore.getInstance().getBookController().reservedsToFieldList();
        List<String> columnNames = new ArrayList<String>();
        columnNames.add("reserveId");
        columnNames.add("Title");
        columnNames.add("Author");
        columnNames.add("ISBN");
        columnNames.add("Year");
        columnNames.add("Gender");
        uiController.listItems(books, columnNames);
    }
}
