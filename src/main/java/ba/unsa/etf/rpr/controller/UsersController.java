package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.controller.components.ActionCellFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.LibraryException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<User,String>("phone"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));

        //Implementing search feature-on click of button, the search method in manager will be called and list in
        //table will be updated

        actionColumn.setCellFactory(new ActionCellFactory(editEvent -> {
            int userId = Integer.parseInt(((Button)editEvent.getSource()).getUserData().toString());
            updateUser(userId);
        }, (deleteEvent -> {
            int userId = Integer.parseInt(((Button)deleteEvent.getSource()).getUserData().toString());
            deleteUser(userId);
        })));
        usersTable.setItems(FXCollections.observableList(userManager.getAll()));
        usersTable.refresh();

    }
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

    public void updateUser(int userId){

    }

    public void deleteUser(int userId){
        Alert confirmation=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to remove this book");
        Optional<ButtonType> result=confirmation.showAndWait();
        if(!result.get().getButtonData().isCancelButton()){
            userManager.delete(userId);
            refreshUsers(userManager.getAll());

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
