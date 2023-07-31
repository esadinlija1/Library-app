package ba.unsa.etf.rpr.controller.components;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ActionButtonFactory<T> implements Callback<TableColumn<T, T>, TableCell<T, T>> {
    @Override
    public TableCell<T, T> call(TableColumn<T, T> ttTableColumn) {
        return null;
    }
}
