package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.BookManager;
import ba.unsa.etf.rpr.domain.Book;
import ba.unsa.etf.rpr.exceptions.LibraryException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/***
 * Controller that manages Books entity
 *
 */
public class BooksController implements Initializable {

    private final BookManager bookManager=new BookManager();

    @FXML
    public AnchorPane booksTab;

    @FXML
    public TableView booksTable;

    @FXML
    public TextField search;

    @FXML
    public TableColumn<Book,Integer> idColumn;

    @FXML
    public TableColumn<Book,String> titleColumn;

    @FXML
    public TableColumn<Book,String> authorColumn;

    private ObservableList<Book> bookObservableList= FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));

        refreshBooks();
    }


    private void refreshBooks(){
            booksTable.setItems(FXCollections.observableList(bookManager.getAll()));
            booksTable.refresh();
    }
}
