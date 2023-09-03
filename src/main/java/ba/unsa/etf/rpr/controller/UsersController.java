package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.BookManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Book;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.LibraryException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.Optional;


/***
 * Controller class for Users tab
 */

public class UsersController {

    private final UserManager userManager=new UserManager();

    @FXML
    public AnchorPane usersTab;

    @FXML
    public TableView usersTable=new TableView();

    @FXML
    public TextField searchKeyWord=new TextField();

    @FXML
    public TableColumn<User,Integer> idColumn=new TableColumn<>("ID");


    @FXML
    public TableColumn<User,String> nameColumn=new TableColumn<>("Name");

    @FXML
    public TableColumn<User,String> emailColumn=new TableColumn<>("email");

    @FXML
    public TableColumn<User,String> phoneColumn=new TableColumn<>("Phone");

    @FXML
    public Button searchButton=new Button();

    @FXML
    public Button addUserButton=new Button();

    @FXML
    public Button backButton=new Button();


    @FXML
    public TableColumn<User,Integer> actionColumn=new TableColumn<>("Actions");




    private ObservableList<User> usersObservableList= FXCollections.observableArrayList();


    /***
     * Method that is used when initializing to get list of all users, but also when we do any kind of change in list, i.e.
     * delete a user or update data of current user
     * @param users
     */
    private void refreshUsers(List<User> users){
        usersTable.setItems(FXCollections.observableList(users));
        usersTable.refresh();
    }


    /***
     * Method that implements search feature for users
     * @param name
     * @throws LibraryException
     */
    @FXML
    public void searchUsers(String name) throws LibraryException {
        refreshUsers(userManager.searchByName(name));
    }

    public void updateUsers(int userId){

    }

    public void deleteUser(int userId){
        Alert confirmation=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to remove this book");
        Optional<ButtonType> result=confirmation.showAndWait();
        if(!result.get().getButtonData().isCancelButton()){
            userManager.delete(userId);
            refreshUsers(userManager.getAll());

        }
    }

}
