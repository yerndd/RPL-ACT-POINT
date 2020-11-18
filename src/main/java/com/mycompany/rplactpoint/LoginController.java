/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rplactpoint;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import com.mycompany.rplactpoint.databases.UserHandler;
import com.mycompany.rplactpoint.databases.model.UserModel;
import com.mycompany.rplactpoint.utilities.SHA1Hash;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author deokr
 */
public class LoginController {

    @FXML TextField usernameUser;
    @FXML PasswordField passwordUser;
    
    private Alert alert;
    
    public void login() throws IOException {        
        UserHandler tbUser = new UserHandler();
        UserModel login = new UserModel(usernameUser.getText(), new SHA1Hash(passwordUser.getText()).getHasil());
        
        UserModel user = tbUser.getLogin(login);
        
        if(user == null) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Gagal");
            alert.setHeaderText(null);
            alert.setContentText("Username atau password salah!");
            alert.showAndWait();
        } else {
            App.loggedIn = user;
            switch (user.getLevelUser()) {
                case 0:
                    App.setRoot("Admin");
                    break;
                case 1:
                    App.setRoot("tugasPetugas");
                    break;
                default:
                    App.setRoot("mahasiswa");
                    break;
            }
        }
    }
    
}
