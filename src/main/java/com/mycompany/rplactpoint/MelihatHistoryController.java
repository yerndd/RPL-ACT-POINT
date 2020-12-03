package com.mycompany.rplactpoint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.rplactpoint.databases.PoinHandler;
import com.mycompany.rplactpoint.databases.model.PoinModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MelihatHistoryController implements Initializable {
    
    @FXML Text loggedIn;
    @FXML TableView historipoinTable;
    @FXML TableColumn<PoinModel, Integer> index;
    @FXML TableColumn<PoinModel, String> namaKegiatan;
    @FXML TableColumn<PoinModel, String> jenisKegiatan;
    @FXML TableColumn<PoinModel, String> tanggalKegiatan;
    @FXML TableColumn<PoinModel, String> tingkatKegiatan;
    @FXML TableColumn<PoinModel, Integer> poinKegiatan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loggedIn.setText(App.loggedIn.getUsernameUser());
        
        index.setCellValueFactory(new PropertyValueFactory<>("index"));
        namaKegiatan.setCellValueFactory(new PropertyValueFactory<>("namaKegiatan"));
        jenisKegiatan.setCellValueFactory(new PropertyValueFactory<>("jenisKegiatan"));
        tanggalKegiatan.setCellValueFactory(new PropertyValueFactory<>("tanggalKegiatan"));
        tingkatKegiatan.setCellValueFactory(new PropertyValueFactory<>("tingkatKegiatan"));
        poinKegiatan.setCellValueFactory(new PropertyValueFactory<>("poinKegiatan"));
        historipoinTable.setItems(poinModel);
    }    
    
    public void kembali() throws IOException {
        App.setRoot("mahasiswa");
    }
    
    private ObservableList<PoinModel> poinModel = new PoinHandler().getHistory();
}
