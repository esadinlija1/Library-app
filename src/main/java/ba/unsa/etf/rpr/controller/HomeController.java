package ba.unsa.etf.rpr.controller;

import java.awt.event.ActionEvent;

public class HomeController {

public void openBooksTab(ActionEvent event){
    openNewTab("/fxml/books.fxml",new BooksController());
}

    private void openNewTab(String file,Object con) {
    }
}
