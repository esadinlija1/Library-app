package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.BookManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Book;
import ba.unsa.etf.rpr.domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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

}
