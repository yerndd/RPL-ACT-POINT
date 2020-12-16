/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rplactpoint;

import com.mycompany.rplactpoint.databases.PoinHandler;
import com.mycompany.rplactpoint.databases.MahasiswaHandler;
import com.mycompany.rplactpoint.databases.model.MahasiswaModel;
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author User
 */
public class RequestPoinController implements Initializable {

    @FXML Text loggedIn;
    
    @FXML TextField nim;
    @FXML TextField namaKegiatan;
    @FXML TextField tanggalKegiatan;
    
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
        MahasiswaHandler tbMahasiswa = new MahasiswaHandler();
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        String tanggal = formatter.format(date);
        PoinModel tambahPoin;
        MahasiswaModel tambahMahasiswa;
        
        tambahMahasiswa = new MahasiswaModel(0, 0, nim.getText(), "", 0, "");
        
        MahasiswaModel dapatMahasiswa = tbMahasiswa.getMahasiswaNim(tambahMahasiswa);
        if(dapatMahasiswa == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gagal");
            alert.setHeaderText(null);
            alert.setContentText("Mahasiswa dengan nim tersebut tidak ada.");
            alert.showAndWait();
            return;
        }
        if (filePilihan != null) {
            File dest = new File("./assets/"+filePilihan.getName());
            dest.getParentFile().mkdirs(); 
            dest.createNewFile();
            copyFileUsingStream(filePilihan, dest);
            tambahPoin = new PoinModel(0, 0, dapatMahasiswa, tanggal, tanggalKegiatan.getText(), jenisKegiatan.getValue().toString(), sebagaiKegiatan.getValue().toString(), tingkatKegiatan.getValue().toString(), namaKegiatan.getText(), 0, filePilihan.getName());
        } else {
            tambahPoin = new PoinModel(0, 0, dapatMahasiswa, tanggal, tanggalKegiatan.getText(), jenisKegiatan.getValue().toString(), sebagaiKegiatan.getValue().toString(), tingkatKegiatan.getValue().toString(), namaKegiatan.getText(), 0, "");
        }
        
        PoinModel dapatPoin = tbPoin.getPoin(tambahPoin);
        if(dapatPoin != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gagal");
            alert.setHeaderText(null);
            alert.setContentText("Request tersebut sudah ada.");
            alert.showAndWait();
            return;
        }
        tbPoin.tambahRequest(tambahPoin);
        
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
    
    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}