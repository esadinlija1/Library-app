package ba.unsa.etf.rpr.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {

public void openBooksTab(ActionEvent event){
    openNewTab("/fxml/books.fxml",new BooksController());
}

    private void openNewTab(String file,Object controller) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource(file));
            loader.setController(controller);
            Stage stage=new Stage();
            stage.setScene(new Scene(loader.<Parent>load(),USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
