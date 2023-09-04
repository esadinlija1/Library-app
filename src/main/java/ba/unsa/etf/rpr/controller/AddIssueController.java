package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.IssuedBooksManager;
import ba.unsa.etf.rpr.domain.Book;
import ba.unsa.etf.rpr.domain.IssuedBook;
import ba.unsa.etf.rpr.exceptions.LibraryException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AddIssueController {

    private IssuedBooksManager issuedBooksManager=new IssuedBooksManager();

    @FXML
    public TextField bookIdfield=new TextField();

    @FXML
    public TextField userIdField=new TextField();

    @FXML
    public TextField dateField=new TextField();

    @FXML
    private void addIssue() throws LibraryException {
        String bookId=bookIdfield.getText().toString();
        Integer bookIdint=Integer.parseInt(bookId);
        String userId=userIdField.getText().toString();
        Integer userIdint=Integer.parseInt(userId);
        issuedBooksManager.add(new IssuedBook(0,bookIdint,userIdint,dateField.getText()));
        backToIssuedBooksTab();
    }

    @FXML
    private void backToIssuedBooksTab(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/issuedbooks.fxml"));
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
