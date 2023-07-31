package ba.unsa.etf.rpr.controller;


import ba.unsa.etf.rpr.business.BookManager;
import ba.unsa.etf.rpr.domain.Book;
import ba.unsa.etf.rpr.exceptions.LibraryException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/***
 * Book adding form controller class
 */
public class AddBookController {

    private final BookManager bookManager=new BookManager();

    @FXML
    private TextField titleField=new TextField();

    @FXML
    private TextField authorField=new TextField();

    @FXML
    private Button addBookButton=new Button();

    @FXML
    private void addBook() throws LibraryException {
        bookManager.add(new Book(0,titleField.getText(),authorField.getText()));
        backToBooksTab();
    }

    @FXML
    private void backToBooksTab(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/books.fxml"));
            //loader.setController(controller);
            Stage stage=new Stage();
            stage.setScene(new Scene(loader.<Parent>load(),USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
