package ba.unsa.etf.rpr.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

/***
 * Controller class for IssuedBooks tab
 */
public class IssuedBookController implements Initializable {

    @FXML
    public TableView issuedBooks=new TableView();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
