/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rplactpoint;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User
 */
public class RequestPoinController implements Initializable {

    @FXML Text loggedIn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loggedIn.setText(App.loggedIn.getUsernameUser());
    }    
    
    public void logout() throws IOException {
        App.loggedIn = null;
        App.setRoot("login");
    }
    
    public void requestPoin() throws IOException {
        App.setRoot("requestPoin");
    }
}
