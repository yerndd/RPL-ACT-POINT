/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rplactpoint;

import com.mycompany.rplactpoint.databases.FeedsHandler;
import com.mycompany.rplactpoint.databases.model.FeedsModel;
import com.mycompany.rplactpoint.utilities.SHA1Hash;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Me
 */
public class EditFeedsController implements Initializable {
    
    @FXML Text loggedIn;
    @FXML TextField judul;
    @FXML TextArea deskripsi;
    @FXML TextField link;

    /**
     * Initializes the controller class.
     */
    
    private Alert alert;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        judul.setText(App.feedEdit.getJudulKegiatan());
        deskripsi.setText(App.feedEdit.getDeskripsiKegiatan());
        link.setText(App.feedEdit.getLinkKegiatan());
        loggedIn.setText(App.loggedIn.getUsernameUser());
    }  
    
    @FXML
    public void editFeeds() throws IOException {
        
        if(judul.getText().equals("") || deskripsi.getText().equals("") || link.getText().equals("")){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gagal");
            alert.setHeaderText(null);
            alert.setContentText("Isikan semua data");
            alert.showAndWait();
            return;
        }
        
        FeedsHandler tbUser = new FeedsHandler();
        FeedsModel edit = new FeedsModel(judul.getText(), deskripsi.getText(), link.getText());
        FeedsModel dapat = tbUser.getJudulKegiatan(edit);
        
        tbUser.editFeeds(edit, App.feedEdit);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Berhasil");
        alert.setHeaderText(null);
        alert.setContentText("Feeds Berhasil diubah");
        alert.showAndWait();

        App.setRoot("listFeeds");
    }
    
    @FXML
    public void kembali() throws IOException {
        App.setRoot("listFeeds");
    }
    
}
