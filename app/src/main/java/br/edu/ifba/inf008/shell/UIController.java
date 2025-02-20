package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.IUIController;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tab;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;

public class UIController extends Application implements IUIController
{
    private MenuBar menuBar;
    private TabPane tabPane;
    private static UIController uiController;

    public UIController() {
    }

    @Override
    public void init() {
        uiController = this;
    }

    public static UIController getInstance() {
        return uiController;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library System");
    
        menuBar = new MenuBar();
    
        tabPane = new TabPane();
        tabPane.setSide(Side.BOTTOM);
    
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(tabPane);
    
        Scene scene = new Scene(borderPane, 960, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    
        Core.getInstance().getIOController().readAllPreviousData();
        Core.getInstance().getPluginController().init();
    }

    @Override
    public void stop() {
        Core.getInstance().getIOController().writeCurrentData();
    }

    public MenuItem createMenuItem(String menuText, String menuItemText) {
        // Criar o menu caso ele nao exista
        Menu newMenu = null;
        for (Menu menu : menuBar.getMenus()) {
            if (menu.getText() == menuText) {
                newMenu = menu;
                break;
            }
        }
        if (newMenu == null) {
            newMenu = new Menu(menuText);
            menuBar.getMenus().add(newMenu);
        }

        // Criar o menu item neste menu
        MenuItem menuItem = new MenuItem(menuItemText);
        newMenu.getItems().add(menuItem);

        return menuItem;
    }

    public Tab createTab(String tabText, Node contents) {
        Tab tab = new Tab();
        tab.setText(tabText);
        tab.setContent(contents);
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);

        return tab;
    }

    public void closeTab(Tab tab) {
        tabPane.getTabs().remove(tab);
    }

    public Tab setFormScene(String title, Node contents, EventHandler<ActionEvent> submitHandler) {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Text tituloCena = new Text(title);
        tituloCena.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(tituloCena, 0, 0, 2, 1);

        gridPane.add(contents, 0, 1, 2, 1);

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(submitHandler);
        gridPane.add(submitButton, 0, 2, 2, 1);

        return createTab(title, gridPane);
    }

    public Node createForm() {
        GridPane form = new GridPane();
        form.setAlignment(Pos.CENTER);
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(25, 25, 25, 25));
    
        return form;
    }

    public Node appendField(Node baseForm, String fieldLabel) {
        if (!(baseForm instanceof GridPane)) {
            return baseForm;
        }
        GridPane form = (GridPane) baseForm;
        
        int maxRow = -1;
        for (Node child : form.getChildren()) {
            Integer row = GridPane.getRowIndex(child);
            
            if (row == null) {
                row = 0;
            }
            if (row > maxRow) {
                maxRow = row;
            }
        }
        int newRow = maxRow + 1;
        
        // Create the new label and text field
        Label newLabel = new Label(fieldLabel + ":");
        TextField newTextField = new TextField();
        
        // Add the new nodes to the GridPane at the new row
        form.add(newLabel, 0, newRow);
        form.add(newTextField, 1, newRow);
        
        return form;
    }

    public void createAlert(String title, String header, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public void createAlert(String title, String header, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Tab listItems(List<List<String>> items, List<String> columnNames) {
        TableView<ObservableList<String>> tableView = new TableView<>();
        
        if(items != null && !items.isEmpty()){
            for (int col = 0; col < columnNames.size(); col++) {
                final int colIndex = col;
                TableColumn<ObservableList<String>, String> tableColumn = new TableColumn<>(columnNames.get(col));
                tableColumn.setCellValueFactory(param -> {
                    ObservableList<String> row = param.getValue();
                    if(colIndex < row.size()){
                        return new SimpleStringProperty(row.get(colIndex));
                    } else {
                        return new SimpleStringProperty("");
                    }
                });
                tableView.getColumns().add(tableColumn);
            }
        }
        
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        for (List<String> row : items) {
            ObservableList<String> rowData = FXCollections.observableArrayList(row);
            data.add(rowData);
        }
        tableView.setItems(data);
        
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPlaceholder(new Label("The table is empty."));
        tableView.setPrefSize(800, 400);
        
        return createTab("List", tableView);
    }
}
