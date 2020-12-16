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
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User
 */
public class LaporanPoinMahasiswaController implements Initializable {
    
    @FXML Text loggedIn;
    @FXML TableView laporanTable;
    @FXML TableColumn<PoinModel, Integer> index;
    @FXML TableColumn<PoinModel, String> nama;
    @FXML TableColumn<PoinModel, String> namaKegiatan;
    @FXML TableColumn<PoinModel, String> jenisKegiatan;
    @FXML TableColumn<PoinModel, String> tanggalKegiatan;
    @FXML TableColumn<PoinModel, String> tingkatKegiatan;
    @FXML TableColumn<PoinModel, Integer> poinKegiatan;
    @FXML ComboBox <String> cbJenis, cbTingkat;
    @FXML TextField search;
    private ObservableList<PoinModel> listLaporan,temp;
    private PoinHandler PoinHandler = new PoinHandler();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loggedIn.setText(App.loggedIn.getUsernameUser());
        cbJenis.getItems().setAll("Semua","Kemahasiswaan","Prestasi","Organsasi");
        cbTingkat.getItems().setAll("Semua","Internal Kampus","DIY","Nasional","Internasional");
        cbJenis.getSelectionModel().selectFirst();
        cbTingkat.getSelectionModel().selectFirst();
        try {
            tampilan();
        } catch (Exception ex) {
            System.err.println("Error");
        }
    }    
    
    public void tampilan(){
        try{
            listLaporan = PoinHandler.getLaporan();
            temp = PoinHandler.getLaporan();
            index.setCellValueFactory(new PropertyValueFactory<>("index"));
            nama.setCellValueFactory(celldata -> new ReadOnlyStringWrapper(celldata.getValue().getMahasiswa().getNama()));
            namaKegiatan.setCellValueFactory(new PropertyValueFactory<>("namaKegiatan"));
            jenisKegiatan.setCellValueFactory(new PropertyValueFactory<>("jenisKegiatan"));
            tanggalKegiatan.setCellValueFactory(new PropertyValueFactory<>("tanggalKegiatan"));
            tingkatKegiatan.setCellValueFactory(new PropertyValueFactory<>("tingkatKegiatan"));
            poinKegiatan.setCellValueFactory(new PropertyValueFactory<>("poinKegiatan"));
            for (PoinModel laporanModel : listLaporan) {
                //menambahkan data ke tabel
                laporanTable.getItems().add(laporanModel);
            }
        } catch(Exception ex){
            ex.printStackTrace();
             System.err.println("Error");
        }
                
    }
    public void hasilFilter(String jk, String tk) throws Exception{        
        temp.clear();
        laporanTable.getItems().clear();
        try{
            ObservableList<PoinModel> listPencarian = listLaporan;
            index.setCellValueFactory(new PropertyValueFactory<>("index"));
            nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
            namaKegiatan.setCellValueFactory(new PropertyValueFactory<>("namaKegiatan"));
            jenisKegiatan.setCellValueFactory(new PropertyValueFactory<>("jenisKegiatan"));
            tanggalKegiatan.setCellValueFactory(new PropertyValueFactory<>("tanggalKegiatan"));
            tingkatKegiatan.setCellValueFactory(new PropertyValueFactory<>("tingkatKegiatan"));
            poinKegiatan.setCellValueFactory(new PropertyValueFactory<>("poinKegiatan"));
            for (PoinModel laporanModel : listLaporan) {
                if(jk.equals("Semua") || tk.equals("Semua")){
                    if(jk.equals("Semua") && tk.equals("Semua")){
                        laporanTable.getItems().add(laporanModel);
                        temp.add(laporanModel);
                    }else{
                        if(jk.equals("Semua")){
                            if(laporanModel.getTingkatKegiatan().equals(tk)){
                                laporanTable.getItems().add(laporanModel);
                                temp.add(laporanModel);
                            }
                        }
                        if(tk.equals("Semua")){
                            if(laporanModel.getJenisKegiatan().equals(jk)){
                                laporanTable.getItems().add(laporanModel);
                                temp.add(laporanModel);
                            }
                        }
                    }
                }else{
                    if(laporanModel.getJenisKegiatan().equals(jk) && laporanModel.getTingkatKegiatan().equals(tk)){
                        laporanTable.getItems().add(laporanModel);
                        temp.add(laporanModel);
                    }
                }

            }
        }
        catch(Exception ex){
            ex.printStackTrace();
             System.err.println("Error");
        }
    }
    
    public void hasilPencarian(String cari) throws Exception{
        laporanTable.getItems().clear();
        try{
            ObservableList<PoinModel> listPencarian = temp;
            //set nilai dari setiap kolom
            index.setCellValueFactory(new PropertyValueFactory<>("index"));
            nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
            namaKegiatan.setCellValueFactory(new PropertyValueFactory<>("namaKegiatan"));
            jenisKegiatan.setCellValueFactory(new PropertyValueFactory<>("jenisKegiatan"));
            tanggalKegiatan.setCellValueFactory(new PropertyValueFactory<>("tanggalKegiatan"));
            tingkatKegiatan.setCellValueFactory(new PropertyValueFactory<>("tingkatKegiatan"));
            poinKegiatan.setCellValueFactory(new PropertyValueFactory<>("poinKegiatan"));
            for (PoinModel laporanModel : listLaporan) {
                if(laporanModel.getNamaKegiatan().toLowerCase().startsWith(cari.toLowerCase())){
                    laporanTable.getItems().add(laporanModel);
                    }
                else if(laporanModel.getMahasiswa().getNama().toLowerCase().startsWith(cari.toLowerCase())){
                    laporanTable.getItems().add(laporanModel);
                }
                else if(cari.equals("")){
                        laporanTable.getItems().add(laporanModel);
                    }
               }
        }catch(Exception ex){
            ex.printStackTrace();
             System.err.println("Error");
        }
    }

    
    @FXML
    private void tampil()throws IOException, Exception{
        String jenisKegiatan = cbJenis.getSelectionModel().getSelectedItem();
        String tingkatKegiatan = cbTingkat.getSelectionModel().getSelectedItem();
        hasilFilter(jenisKegiatan, tingkatKegiatan);
    }
    
    @FXML
    private void cari()throws IOException, Exception{
        hasilPencarian(search.getText());
    }
                
    public void kembali() throws IOException {
        App.setRoot("tugasPetugas");
    }
    
}
