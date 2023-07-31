package ba.unsa.etf.rpr.controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {


    private void openNewTab(String file,Object controller) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource(file));
            //loader.setController(controller);
            Stage stage=new Stage();
            stage.setScene(new Scene(loader.<Parent>load(),USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeApp(ActionEvent actionEvent){
        Platform.exit();
        System.exit(0);
    }

    public void openBooksTab(javafx.event.ActionEvent actionEvent) {
        openNewTab("/fxml/books.fxml",new BooksController());
    }

    public void openUsersTab(javafx.event.ActionEvent actionEvent) {
        openNewTab("/fxml/users.fxml",new UsersController());
    }
}
