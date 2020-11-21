/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rplactpoint;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.mycompany.rplactpoint.databases.model.FeedsModel;
import com.mycompany.rplactpoint.databases.FeedsHandler;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author Me
 */
public class MelihatFeedsController implements Initializable {
    
    @FXML Text loggedIn;
    @FXML TableView melihatFeedsTable;
    @FXML TableColumn<FeedsModel, String> judulKegiatan;
    @FXML TableColumn<FeedsModel, String> deskripsiKegiatan;
    @FXML TableColumn<FeedsModel, String> linkKegiatan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loggedIn.setText(App.loggedIn.getUsernameUser());
        judulKegiatan.setCellValueFactory(new PropertyValueFactory<>("judulKegiatan"));
        deskripsiKegiatan.setCellValueFactory(new PropertyValueFactory<>("deskripsiKegiatan"));
        linkKegiatan.setCellValueFactory(new PropertyValueFactory<>("linkKegiatan"));
        melihatFeedsTable.setItems(studentsModels);
    }
    
    
    public void kembali() throws IOException {
        App.setRoot("mahasiswa");
    }
    
    private ObservableList<FeedsModel> studentsModels = new FeedsHandler().getFeeds();
    
}
