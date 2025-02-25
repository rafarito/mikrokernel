package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;
import javafx.scene.control.MenuItem;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class LateBooksPluginReport implements IPlugin
{
    public boolean init() {
        IUIController uiController = ICore.getInstance().getUIController();

        MenuItem listReservedFine = uiController.createMenuItem("Report", "List late reserved books");
        listReservedFine.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                listReservedBooksFine();
            }
        });

        return true;
    }

    private void listReservedBooksFine() {
        IUIController uiController = ICore.getInstance().getUIController();
        List<List<String>> books = ICore.getInstance().getBookController().reservedsToFieldList();

        Iterator<List<String>> iterator = books.iterator();
        while (iterator.hasNext()) {
            List<String> book = iterator.next();
            int reserveId = Integer.parseInt(book.get(0));
            try {
                Date loanDate = ICore.getInstance().getBookController().searchReservedBook(reserveId).getLoanDate();
                Date currentDate = new Date();
                long days = daysBetween(loanDate, currentDate);
                
                if (days <= 14) {
                    iterator.remove();
                    continue;
                }

                double fine = (days - 14) * 0.5;                
                if (fine == 0) {
                    iterator.remove();
                    continue;
                }
        
                book.add(String.valueOf(fine));
            } catch (Exception ex) {
                iterator.remove();
            }
        }
        List<String> columnNames = new ArrayList<String>();
        columnNames.add("reserveId");
        columnNames.add("Title");
        columnNames.add("Author");
        columnNames.add("ISBN");
        columnNames.add("Year");
        columnNames.add("Gender");
        columnNames.add("Fine");
        uiController.listItems(books, columnNames);
    }

    public long daysBetween(Date date1, Date date2) {
        LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.DAYS.between(localDate1, localDate2);
    }
}
