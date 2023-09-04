package ba.unsa.etf.rpr.controller;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
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

public class EditUserController {

    private Integer editUserId;
    private UserManager userManager=new UserManager();

    public EditUserController(Integer editUserId){
        this.editUserId=editUserId;
    }

    @FXML
    private TextField nameField=new TextField();

    @FXML
    private TextField phoneField=new TextField();

    @FXML
    private TextField emailField=new TextField();


    @FXML
    private void editUser() throws LibraryException {

        userManager.update(new User(this.editUserId, nameField.getText(), emailField.getText(), phoneField.getText()));
        backToUsersTab();

    }


    private void backToUsersTab(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/users.fxml"));
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
