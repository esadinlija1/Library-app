package ba.unsa.etf.rpr.controller.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ActionButtonFactory<T> implements Callback<TableColumn<T, T>, TableCell<T, T>> {

    private final EventHandler<ActionEvent> buttonOne;
    private final EventHandler<ActionEvent> buttonTwo;


    public ActionButtonFactory(EventHandler<ActionEvent> buttonOne, EventHandler<ActionEvent> buttonTwo){
        this.buttonOne = buttonOne;
        this.buttonTwo = buttonTwo;
    }

    @Override
    public TableCell<T, T> call(TableColumn<T, T> ttTableColumn) {
        return null;
    }
}
