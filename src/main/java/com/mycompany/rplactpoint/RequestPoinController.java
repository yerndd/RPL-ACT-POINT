/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rplactpoint;

import com.mycompany.rplactpoint.databases.PoinHandler;
import com.mycompany.rplactpoint.databases.model.PoinModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author User
 */
public class RequestPoinController implements Initializable {

    @FXML Text loggedIn;
    
    @FXML TextField nim;
    @FXML TextField nama;
    @FXML TextField namaKegiatan;
    
    @FXML ComboBox jenisKegiatan;
    @FXML ComboBox sebagaiKegiatan;
    @FXML ComboBox tingkatKegiatan;
    
    ObservableList<String> jenisKegiatanList = FXCollections.observableArrayList("Kemahasiswaan", "Prestasi", "Organisasi");
    
    ObservableList<String> sebagaiKemahasiswaanList = FXCollections.observableArrayList("OKA", "P3DM", "P2KMM", "Makrab Prodi", "Upacara Peringatan Hari Besar Nasional", "Studi Banding Luar Kota", "Volunteer Kerja Sosial");
    ObservableList<String> sebagaiPrestasiList = FXCollections.observableArrayList("Juara 1", "Juara 2", "Juara 3", "Juara Harapan 1", "Juara Harapan 2", "Pembiacara Seminar", "Moderator Seminar", "Peserta Seminar", "Penulis Artikel");
    ObservableList<String> sebagaiOrganisasiList = FXCollections.observableArrayList("Ketua", "Wakil Ketua", "Sekretaris/Bendahara", "Koordinator Departemen", "Anggota", "Ketua UKM", "Wakil Ketua UKM", "Sekretaris/Bendahara UKM", "Koordinator Departemen UKM", "Anggota UKM");
    
    ObservableList<String> tingkatKegiatanList = FXCollections.observableArrayList("Internal Kampus", "DIY", "Nasional", "Internasional");
            
    private FileChooser fileChooser;
    
    private File filePilihan;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileChooser = new FileChooser();
        
        loggedIn.setText(App.loggedIn.getUsernameUser());
        
        jenisKegiatan.setItems(jenisKegiatanList);
        jenisKegiatan.setValue("Kemahasiswaan");
        
        setSebagaiList();
        
        tingkatKegiatan.setItems(tingkatKegiatanList);
        tingkatKegiatan.setValue("Internal Kampus");
        
    }    
    
    public void kembali() throws IOException {
        App.setRoot("mahasiswa");
    }
    
    public void setSebagaiList() {
        String jenis = jenisKegiatan.getValue().toString();
        
        switch(jenis) {
            case "Kemahasiswaan":
                sebagaiKegiatan.setItems(sebagaiKemahasiswaanList);
                sebagaiKegiatan.setValue("OKA");
                break;
            case "Prestasi":
                sebagaiKegiatan.setItems(sebagaiPrestasiList);
                sebagaiKegiatan.setValue("Juara 1");
                break;
            case "Organisasi":
                sebagaiKegiatan.setItems(sebagaiOrganisasiList);
                sebagaiKegiatan.setValue("Ketua");
                break;
        }
    }
    
    public void requestPoin() throws IOException {
        PoinHandler tbPoin = new PoinHandler();
        PoinModel tambah = new PoinModel(0, 0, nim.getText(), nama.getText(), jenisKegiatan.getValue().toString(), sebagaiKegiatan.getValue().toString(), tingkatKegiatan.getValue().toString(), namaKegiatan.getText(), filePilihan.getName());
        PoinModel dapat = tbPoin.getPoin(tambah);
        if(dapat != null) {
            if(dapat.getNama() == null && dapat.getNim() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Gagal");
                alert.setHeaderText(null);
                alert.setContentText("Request tersebut sudah ada.");
                alert.showAndWait();
                return;
            }
        }
        tbPoin.tambahRequest(tambah);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Berhasil");
        alert.setHeaderText(null);
        alert.setContentText("Request Berhasil ditambahkan");
        alert.showAndWait();

        App.setRoot("mahasiswa");
    }
    
    public void chooseFile() {
        FileChooser.ExtensionFilter pictureFilter = new FileChooser.ExtensionFilter("Pictures files (*.png, *.jpg, *.jpeg)", "*.png", "*.jpg", "*.jpeg");
        
        fileChooser.getExtensionFilters().add(pictureFilter);
        
        this.filePilihan = fileChooser.showOpenDialog(App.stageee);
    }
}
