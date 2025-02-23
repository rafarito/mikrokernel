package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class LoanPlugin implements IPlugin
{
    public boolean init() {
        IUIController uiController = ICore.getInstance().getUIController();

        MenuItem borrowBookItem = uiController.createMenuItem("Loan", "Borrow book");
        borrowBookItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                borrowBook();
            }
        });

        MenuItem returnBookItem = uiController.createMenuItem("Loan", "Return book");
        returnBookItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                returnBook();
            }
        });

        MenuItem searchLoanItem = uiController.createMenuItem("Loan", "search loan");
        searchLoanItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                searchLoanByUser();
            }
        });

        return true;
    }

    private void borrowBook() {
        IUIController uiController = ICore.getInstance().getUIController();
        Node form = uiController.createForm();
        uiController.appendField(form, "Username");
        uiController.appendField(form, "Title of the book");
        uiController.appendField(form, "Date of the loan", "dd/mm/yyyy");
        uiController.setFormScene("borrow Book", form, new EventHandler<ActionEvent>() {
        @Override
            public void handle(ActionEvent e) {
                if (form instanceof GridPane) {
                    GridPane grid = (GridPane) form;
                    int i = 0;
                    String[] values = new String[3];

                    for (Node node : grid.getChildren()) {
                        if (node instanceof TextField) {
                            TextField textField = (TextField) node;
                            values[i++] = textField.getText();
                        }
                    }
                    try{
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date loanDate =  formatter.parse(values[2]);
                        ICore.getInstance().getUserController().borrowBook(values[0], values[1], loanDate);
                        uiController.createAlert("Book borrowed", "Book borrowed", "Book borrowed successfully");
                    } catch (Exception ex) {
                        uiController.createAlert("Error", "One error ocourred", ex.getMessage(), AlertType.ERROR);
                    }
                }
            }
        });
    }

    private void returnBook(){
        IUIController uiController = ICore.getInstance().getUIController();
        Node form = uiController.createForm();
        uiController.appendField(form, "Username");
        uiController.appendField(form, "Reserve ID");
        uiController.setFormScene("Return Book", form, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (form instanceof GridPane) {
                    GridPane grid = (GridPane) form;
                    int i = 0;
                    String[] values = new String[2];

                    for (Node node : grid.getChildren()) {
                        if (node instanceof TextField) {
                            TextField textField = (TextField) node;
                            values[i++] = textField.getText();
                        }
                    }
                    try{
                        ICore.getInstance().getUserController().returnBook(values[0], Integer.parseInt(values[1]));
                        uiController.createAlert("Book returned", "Book returned", "Book returned successfully");
                    } catch (Exception ex) {
                        uiController.createAlert("Error", "One error ocourred", ex.getMessage(), AlertType.ERROR);
                    }
                }
            }
        });
    }

    private void searchLoanByUser(){
        IUIController uiController = ICore.getInstance().getUIController();
        Node form = uiController.createForm();
        uiController.appendField(form, "Username");
        uiController.setFormScene("Search Loan by User", form, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (form instanceof GridPane) {
                    GridPane grid = (GridPane) form;
                    for (Node node : grid.getChildren()) {
                        if (node instanceof TextField) {
                            TextField textField = (TextField) node;
                            String value = textField.getText();
                            List<String> columnNames = new ArrayList<String>();
                            columnNames.add("Username");
                            columnNames.add("Reserve ID");
                            columnNames.add("Title");
                            columnNames.add("Author");
                            columnNames.add("ISBN");
                            columnNames.add("Gender");
                            columnNames.add("Year");
                            columnNames.add("Loan Date");
                            try {
                                uiController.listItems(ICore.getInstance().getUserController().searchLoanByUser(value), columnNames);
                                ICore.getInstance().getUserController().searchLoanByUser(value);
                            } catch (UnsupportedOperationException ex) {
                                uiController.createAlert("Error", "One error ocourred", ex.getMessage(), AlertType.ERROR);
                            }
                        }
                    }
                }
            }
        });
    }
}
