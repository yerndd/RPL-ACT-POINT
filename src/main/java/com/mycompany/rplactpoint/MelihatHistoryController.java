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
public class MelihatHistoryController implements Initializable {
    
    @FXML Text loggedIn;
    @FXML TableView historipoinTable;
    @FXML TableColumn<PoinModel, Integer> index;
    @FXML TableColumn<PoinModel, String> namaKegiatan;
    @FXML TableColumn<PoinModel, String> jenisKegiatan;
    @FXML TableColumn<PoinModel, String> tanggalKegiatan;
    @FXML TableColumn<PoinModel, String> tingkatKegiatan;
    @FXML TableColumn<PoinModel, Integer> poinKegiatan;
    @FXML ComboBox <String> cbJenis, cbTingkat;
    @FXML TextField search;
    private ObservableList<PoinModel> listHistory,temp;
    private PoinHandler PoinHandler = new PoinHandler();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loggedIn.setText(App.loggedIn.getUsernameUser());
        cbJenis.getItems().setAll("Semua","Kemahasiswaan","Prestasi","Organsasi");
        cbTingkat.getItems().setAll("Semua","Internal","DIY","Nasional","Internasional");
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
            listHistory = PoinHandler.getHistory(App.loggedIn.getUsernameUser());
            temp = PoinHandler.getHistory(App.loggedIn.getUsernameUser());
            index.setCellValueFactory(new PropertyValueFactory<>("index"));
            namaKegiatan.setCellValueFactory(new PropertyValueFactory<>("namaKegiatan"));
            jenisKegiatan.setCellValueFactory(new PropertyValueFactory<>("jenisKegiatan"));
            tanggalKegiatan.setCellValueFactory(new PropertyValueFactory<>("tanggalKegiatan"));
            tingkatKegiatan.setCellValueFactory(new PropertyValueFactory<>("tingkatKegiatan"));
            poinKegiatan.setCellValueFactory(new PropertyValueFactory<>("poinKegiatan"));
//            historipoinTable.setItems(poinModel);
            for (PoinModel historyModel : listHistory) {
                //menambahkan data ke tabel
                historipoinTable.getItems().add(historyModel);
            }
        } catch(Exception ex){
            ex.printStackTrace();
             System.err.println("Error");
        }
                
    }
    public void hasilFilter(String jk, String tk) throws Exception{        
        temp.clear();
        historipoinTable.getItems().clear();
        try{
            ObservableList<PoinModel> listPencarian = listHistory;
            index.setCellValueFactory(new PropertyValueFactory<>("index"));
            namaKegiatan.setCellValueFactory(new PropertyValueFactory<>("namaKegiatan"));
            jenisKegiatan.setCellValueFactory(new PropertyValueFactory<>("jenisKegiatan"));
            tanggalKegiatan.setCellValueFactory(new PropertyValueFactory<>("tanggalKegiatan"));
            tingkatKegiatan.setCellValueFactory(new PropertyValueFactory<>("tingkatKegiatan"));
            poinKegiatan.setCellValueFactory(new PropertyValueFactory<>("poinKegiatan"));
            for (PoinModel historyModel : listHistory) {
                if(jk.equals("Semua") || tk.equals("Semua")){
                    if(jk.equals("Semua") && tk.equals("Semua")){
                        historipoinTable.getItems().add(historyModel);
                        temp.add(historyModel);
                    }else{
                        if(jk.equals("Semua")){
                            if(historyModel.getTingkatKegiatan().equals(tk)){
                                historipoinTable.getItems().add(historyModel);
                                temp.add(historyModel);
                            }
                        }
                        if(tk.equals("Semua")){
                            if(historyModel.getJenisKegiatan().equals(jk)){
                                historipoinTable.getItems().add(historyModel);
                                temp.add(historyModel);
                            }
                        }
                    }
                }else{
                    if(historyModel.getJenisKegiatan().equals(jk) && historyModel.getTingkatKegiatan().equals(tk)){
                        historipoinTable.getItems().add(historyModel);
                        temp.add(historyModel);
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
        historipoinTable.getItems().clear();
        try{
            ObservableList<PoinModel> listPencarian = temp;
            //set nilai dari setiap kolom
            index.setCellValueFactory(new PropertyValueFactory<>("index"));
            namaKegiatan.setCellValueFactory(new PropertyValueFactory<>("namaKegiatan"));
            jenisKegiatan.setCellValueFactory(new PropertyValueFactory<>("jenisKegiatan"));
            tanggalKegiatan.setCellValueFactory(new PropertyValueFactory<>("tanggalKegiatan"));
            tingkatKegiatan.setCellValueFactory(new PropertyValueFactory<>("tingkatKegiatan"));
            poinKegiatan.setCellValueFactory(new PropertyValueFactory<>("poinKegiatan"));
            for (PoinModel historyModel : listHistory) {
                if(historyModel.getNamaKegiatan().toLowerCase().startsWith(cari.toLowerCase())){
                        historipoinTable.getItems().add(historyModel);
                    }
                else if(cari.equals("")){
                        historipoinTable.getItems().add(historyModel);
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
        App.setRoot("mahasiswa");
    }
    
    ///private ObservableList<PoinModel> poinModel = new PoinHandler().getHistory(App.loggedIn.getUsernameUser());
}
