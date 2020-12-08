/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rplactpoint;

import com.mycompany.rplactpoint.databases.FeedsHandler;
import com.mycompany.rplactpoint.databases.model.FeedsModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        judul.setText(App.feedEdit.getJudulKegiatan());
        loggedIn.setText(App.loggedIn.getUsernameUser());
    }  
    
    @FXML
    public void editFeeds() throws IOException {
        FeedsHandler tbUser = new FeedsHandler();
        FeedsModel edit = new FeedsModel(judul.getText(), deskripsi.getText(), link.getText());
        FeedsModel dapat = tbUser.getJudulKegiatan(edit);
        if(dapat != null) {
            if(dapat.getJudulKegiatan()== null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Gagal");
                alert.setHeaderText(null);
                alert.setContentText("Feeds dengan judul tersebut sudah ada");
                alert.showAndWait();
                return;
            }
        }
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
