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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * FXML Controller class
 *
 * @author Me
 */
public class DetailFeedsController implements Initializable {
    
    @FXML Text loggedIn;
    @FXML Text judul;
    @FXML Text deskripsi;
    @FXML Text link;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        judul.setText(App.feedEdit.getJudulKegiatan());
        deskripsi.setText(App.feedEdit.getDeskripsiKegiatan());
        link.setText(App.feedEdit.getLinkKegiatan());
        loggedIn.setText(App.loggedIn.getUsernameUser());
    }
    
     @FXML
    public void kembali() throws IOException {
        App.setRoot("melihatFeeds");
    }
    
}
