package ba.unsa.etf.rpr.controller.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

public class ActionButtons<T> extends TableCell<T,T> {
    private Button update;
    private Button delete;

    public ActionButtons(javafx.event.EventHandler<javafx.event.ActionEvent> buttonOne, EventHandler<ActionEvent> buttonTwo){
        update=new Button("Update");
        update.setOnAction(buttonOne);
        delete=new Button("Delete");
        delete.setOnAction(buttonTwo);
    }

    @Override
    protected void updateItem(T o,boolean b){
        super.updateItem(o,b);
        if(o!=null){
            HBox box=new HBox();
            box.setAlignment(Pos.CENTER);
            update.setUserData(o);
            delete.setUserData(o);
            box.getChildren().add(update);
            box.getChildren().add(delete);
            setGraphic(box);
        }
    }
}
