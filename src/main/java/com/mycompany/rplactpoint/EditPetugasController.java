/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rplactpoint;

import com.mycompany.rplactpoint.databases.UserHandler;
import com.mycompany.rplactpoint.databases.model.UserModel;
import com.mycompany.rplactpoint.utilities.SHA1Hash;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author deokr
 */
public class EditPetugasController implements Initializable {

   
    @FXML Text loggedIn;
    @FXML TextField username;
    @FXML PasswordField password;
    
    /**
     * Initializes the controller class.
     */
    
     private Alert alert;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText(App.userEdit.getUsernameUser());
        loggedIn.setText(App.loggedIn.getUsernameUser());
    }
    
    @FXML
    public void editPetugas() throws IOException {
        if(username.getText().equals("") || password.getText().equals("")){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gagal");
            alert.setHeaderText(null);
            alert.setContentText("Isikan semua data");
            alert.showAndWait();
            return;
    }
        UserHandler tbUser = new UserHandler();
        UserModel edit = new UserModel(username.getText(), new SHA1Hash(password.getText()).getHasil(), 1);
        UserModel dapat = tbUser.getUser(edit);
       
        tbUser.editPetugas(edit, App.userEdit);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Berhasil");
        alert.setHeaderText(null);
        alert.setContentText("Petugas Berhasil diubah");
        alert.showAndWait();

        App.setRoot("listPetugas");
    }
    
    @FXML
    public void kembali() throws IOException {
        App.setRoot("listPetugas");
    }
}
