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
public class TambahFeedsController implements Initializable {


    @FXML Text loggedIn;
    @FXML TextField judul;
    @FXML TextArea deskripsi;
    @FXML TextField link;
    
    private Alert alert;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loggedIn.setText(App.loggedIn.getUsernameUser());
    }   

    public void tambah() throws IOException { 
        
        if(judul.getText().equals("") || deskripsi.getText().equals("") || link.getText().equals("")){
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gagal");
        alert.setHeaderText(null);
        alert.setContentText("Isikan semua data");
        alert.showAndWait();
        return;
        }
        
        FeedsHandler tbFeeds = new FeedsHandler();
        FeedsModel tambah = new FeedsModel(judul.getText(), deskripsi.getText(), link.getText());
        FeedsModel dapat = tbFeeds.getJudulKegiatan(tambah);
        if(dapat != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gagal");
            alert.setHeaderText(null);
            alert.setContentText("Kegiatan dengan judul tersebut sudah ada");
            alert.showAndWait();
            return;
        }
        tbFeeds.tambahFeeds(tambah);
        
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Berhasil");
        alert.setHeaderText(null);
        alert.setContentText("Feeds Berhasil ditambahkan");
        alert.showAndWait();

        App.setRoot("tugasPetugas");
    }
    
    public void kembali() throws IOException {
        App.setRoot("tugasPetugas");
    }

}
