package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.controller.components.ActionCellFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.LibraryException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
 * Controller class for Users tab
 */

public class UsersController implements Initializable {

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
    public TableColumn<User,String> emailColumn=new TableColumn<>("Email");

    @FXML
    public TableColumn<User,String> phoneColumn=new TableColumn<>("Phone");

    @FXML
    public Button searchButton=new Button();

    @FXML
    public Button addUserButton=new Button();

    @FXML
    public Button backButton=new Button();


    @FXML
    public TableColumn<User,Integer> actionColumn=new TableColumn<>("ActionsColumn");




    private ObservableList<User> usersObservableList= FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<User,String>("phone"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));



        actionColumn.setCellFactory(new ActionCellFactory(editEvent -> {
            int userId = Integer.parseInt(((Button)editEvent.getSource()).getUserData().toString());
            updateUser(userId);
        }, (deleteEvent -> {
            int userId = Integer.parseInt(((Button)deleteEvent.getSource()).getUserData().toString());
            deleteUser(userId);
        })));
       refreshUsers(userManager.getAll());
        System.out.println(userManager.getAll().size());

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






    public void updateUser(int userId){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/edituser.fxml"));
            loader.setController(new EditUserController(userId));
            Stage stage=new Stage();
            stage.setScene(new Scene(loader.<Parent>load(),USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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


    /***
     * Method that implements search feature for users
     * @param actionEvent
     * @throws LibraryException
     */
    public void searchUsers(ActionEvent actionEvent) throws LibraryException {
        refreshUsers(userManager.searchByName(searchKeyWord.getText()));
    }

    @FXML
    private void openAddUserTab(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/adduser.fxml"));
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
