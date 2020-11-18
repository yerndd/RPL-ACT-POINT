/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rplactpoint;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.mycompany.rplactpoint.databases.model.UserModel;
import com.mycompany.rplactpoint.databases.UserHandler;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author deokr
 */
public class ListPetugasController implements Initializable {

    @FXML Text loggedIn;
    @FXML TableView petugasTable;
    @FXML TableColumn<UserModel, Integer> idPetugas;
    @FXML TableColumn<UserModel, String> namaPetugas;
    @FXML TableColumn<UserModel, Integer> editPetugas;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loggedIn.setText(App.loggedIn.getUsernameUser());
        petugasTable.setItems(studentsModels);
    }
    
    public void kembali() throws IOException {
        App.setRoot("Admin");
    }
    
    private ObservableList<UserModel> studentsModels = new UserHandler().getPetugas();
    
}
