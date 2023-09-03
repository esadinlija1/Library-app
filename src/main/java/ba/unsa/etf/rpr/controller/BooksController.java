package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.BookManager;
import ba.unsa.etf.rpr.controller.components.ActionCellFactory;
import ba.unsa.etf.rpr.domain.Book;
import ba.unsa.etf.rpr.exceptions.LibraryException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/***
 * Controller that manages Books entity
 *
 */
public class BooksController implements Initializable {

    private final BookManager bookManager=new BookManager();

    @FXML
    public AnchorPane booksTab;

    @FXML
    public TableView booksTable=new TableView();

    @FXML
    public TextField searchKeyWord=new TextField();

    @FXML
    public TableColumn<Book,Integer> idColumn=new TableColumn<>("ID");


    @FXML
    public TableColumn<Book,String> titleColumn=new TableColumn<>("Title");

    @FXML
    public TableColumn<Book,String> authorColumn=new TableColumn<>("Author");

    @FXML
    public Button searchButton=new Button();

    @FXML
    public Button addBookButton=new Button();

    @FXML
    public Button backButton=new Button();


    @FXML
    public TableColumn<Book,Integer> actionColumn=new TableColumn<>("Actions");




    private ObservableList<Book> bookObservableList= FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        idColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("id"));

        //Implementing search feature-on click of button, the search method in manager will be called and list in
        //table will be updated

        actionColumn.setCellFactory(new ActionCellFactory(editEvent -> {
            int bookId = Integer.parseInt(((Button)editEvent.getSource()).getUserData().toString());
            updateBook(bookId);
        }, (deleteEvent -> {
            int bookId = Integer.parseInt(((Button)deleteEvent.getSource()).getUserData().toString());
            try {
                deleteBook(bookId);
            } catch (LibraryException e) {
                throw new RuntimeException(e);
            }
        })));
        booksTable.setItems(FXCollections.observableList(bookManager.getAll()));
        booksTable.refresh();

    }

    private void deleteBook(int bookId) throws LibraryException {

            Alert confirmation=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to remove this book");
            Optional<ButtonType> result=confirmation.showAndWait();
            if(!result.get().getButtonData().isCancelButton()){
                bookManager.delete(bookId);
                refreshBooks(bookManager.getAll());

        }
    }

    private void updateBook(int bookId) {
    }


    /***
     * Method used when we need to refresh contents of table
     * @param books-books that should be shown in table
     */

    private void refreshBooks(List<Book> books){
           booksTable.setItems(FXCollections.observableList(books));
           booksTable.refresh();
    }



    @FXML
    private void openAddBookTab(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/addbook.fxml"));
            //loader.setController(controller);
            Stage stage=new Stage();
            stage.setScene(new Scene(loader.<Parent>load(),USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void backToHomeTab(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
            //loader.setController(controller);
            Stage stage=new Stage();
            stage.setScene(new Scene(loader.<Parent>load(),USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void searchBooks() throws LibraryException {
        refreshBooks(bookManager.searchByTitle(searchKeyWord.getText()));
    }







}
