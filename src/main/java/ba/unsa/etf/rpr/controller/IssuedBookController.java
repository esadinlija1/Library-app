package ba.unsa.etf.rpr.controller;


import ba.unsa.etf.rpr.business.IssuedBooksManager;
import ba.unsa.etf.rpr.controller.components.ActionCellFactory;
import ba.unsa.etf.rpr.domain.IssuedBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/***
 * Controller class for IssuedBooks tab
 */
public class IssuedBookController implements Initializable {

    private IssuedBooksManager issuedBooksManager=new IssuedBooksManager();

    @FXML
    public TableView issuedBooks=new TableView();

    @FXML
    public TableColumn<IssuedBook,Integer> idColumn=new TableColumn<>("ID");

    @FXML
    public TableColumn<IssuedBook,Integer> bookIdColumn=new TableColumn<>("BookId");

    @FXML
    public TableColumn<IssuedBook,Integer> userIdColumn=new TableColumn<>("UserId");

   @FXML
   public TableColumn<IssuedBook,String> dateColumn=new TableColumn<>("IssueDate");

   @FXML
   public TableColumn<IssuedBook,Integer> actionColumn=new TableColumn<>("Action Column");

   private ObservableList<IssuedBook> issuedBooksObservableList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            idColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook,Integer>("id"));
            bookIdColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook,Integer>("bookID"));
            userIdColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook,Integer>("userID"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook,String>("issueDate"));
            actionColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook,Integer>("id"));

        actionColumn.setCellFactory(new ActionCellFactory(editEvent -> {
            int issuedBookId = Integer.parseInt(((Button)editEvent.getSource()).getUserData().toString());
        }, (deleteEvent -> {
            int issuedBookId = Integer.parseInt(((Button)deleteEvent.getSource()).getUserData().toString());
            deleteIssue(issuedBookId);
        })));

        refreshIssues(issuedBooksManager.getAll());
    }

    private void deleteIssue(int issuedBookId) {
        issuedBooksManager.delete(issuedBookId);
    }

    private void refreshIssues(List<IssuedBook> l){
        issuedBooks.setItems(FXCollections.observableList(l));
        issuedBooks.refresh();
    }

    @FXML
    private void openAddIssueTab(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/addissue.fxml"));
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


}
