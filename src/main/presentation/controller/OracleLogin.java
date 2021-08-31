package main.presentation.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.application.App;
import main.application.service.LoginService;
import main.bdo.OracleUser;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OracleLogin implements Initializable{



    @FXML
    private TextField oracleUsernameTextfield;

    @FXML
    private PasswordField oraclePasswordField;

    @FXML
    private ChoiceBox connection_chooser;


    OracleUser oracleUser;
    LoginService loginService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginService = LoginService.getInstance();
        oracleUser = OracleUser.getInstance();
    }

    @FXML
    void login(ActionEvent event) {
        String connectionString = connection_chooser.getValue().toString();
        if(isValid()){
            oracleUser.setData(oracleUsernameTextfield.getText(), oraclePasswordField.getText());
            if(loginService.login("oracle", connectionString)){
                System.out.println("Login at Oracle was successful");
                App.getInstance().loadWindow("/fxml/PgLogin.fxml", "PG Login");
            }else{
                oracleUsernameTextfield.setStyle("-fx-border-color: red;");
                oraclePasswordField.setStyle("-fx-border-color: red;");
            }
        }

    }
    @FXML
    void quit(ActionEvent event) {
        System.exit(1);
    }

    boolean isValid(){
        return !oracleUsernameTextfield.getText().isEmpty() && !oraclePasswordField.getText().isEmpty() && !connection_chooser.getValue().toString().equals("select connection");
    }
}
