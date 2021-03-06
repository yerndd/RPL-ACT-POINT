package com.mycompany.rplactpoint;

import com.mycompany.rplactpoint.databases.UserHandler;
import com.mycompany.rplactpoint.databases.model.UserModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;

/*
 * FXML Controller class
 *
 * @author Me
 */
public class TugasPetugasController implements Initializable {
    
    @FXML Text loggedIn;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loggedIn.setText(App.loggedIn.getUsernameUser());
    }    
    
    public void logout() throws IOException {
        App.loggedIn = null;
        App.setRoot("login");
    }
    
    public void verifikasiPoin() throws IOException {
        App.setRoot("verifikasiPoin");
    }
    
    public void listFeeds() throws IOException {
        App.setRoot("listFeeds");
    }
    
    public void laporanPoinMahasiswa() throws IOException {
        App.setRoot("laporanPoinMahasiswa");
    }
    
    public void tambahFeeds() throws IOException {
        App.setRoot("tambahFeeds");
    }
    
}
