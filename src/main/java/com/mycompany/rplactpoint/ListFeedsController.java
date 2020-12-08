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
public class ListFeedsController implements Initializable {
    
    @FXML Text loggedIn;
    @FXML TableView feedsTable;
    @FXML TableColumn<FeedsModel, String> judul;
    @FXML TableColumn<FeedsModel, String> deskripsi;
    @FXML TableColumn<FeedsModel, String> link;
    @FXML TableColumn<FeedsModel, Void> edit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loggedIn.setText(App.loggedIn.getUsernameUser());
        judul.setCellValueFactory(new PropertyValueFactory<>("judulKegiatan"));
        deskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsiKegiatan"));
        link.setCellValueFactory(new PropertyValueFactory<>("linkKegiatan"));
        edit.setCellValueFactory(new PropertyValueFactory<>("editFeeds"));
        feedsTable.setItems(studentsModels);  
        
        Callback<TableColumn<FeedsModel, Void>, TableCell<FeedsModel, Void>> cellFactory = new Callback<TableColumn<FeedsModel, Void>, TableCell<FeedsModel, Void>>() {
            @Override
            public TableCell<FeedsModel, Void> call(final TableColumn<FeedsModel, Void> param) {
                final TableCell<FeedsModel, Void> cell = new TableCell<FeedsModel, Void>() {
                    private final Button btnEdit = new Button("Edit");
                    private final Button btnHapus = new Button("Hapus");
                    private final HBox box = new HBox();

                    {
                        btnEdit.setOnAction((ActionEvent event) -> {
                            FeedsModel data = getTableView().getItems().get(getIndex());
                            try {
                                App.feedEdit = data;
                                App.setRoot("editFeeds");
                            } catch(IOException e) {
                                System.out.print(e.getMessage());
                            }
                        });
                        
                        btnHapus.setOnAction((ActionEvent event) -> {
                            FeedsModel data = getTableView().getItems().get(getIndex());
                            FeedsHandler tbUser = new FeedsHandler();
                            tbUser.hapusFeeds(data);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Berhasil");
                            alert.setHeaderText(null);
                            alert.setContentText("Feeds Berhasil dihapus");
                            alert.showAndWait();
                            try {
                                App.setRoot("listFeeds");
                            } catch(IOException e) {
                                System.out.print(e.getMessage());
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        box.getChildren().addAll(btnEdit, btnHapus);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(box);
                        }
                    }
                };
                return cell;
            }
        };
        edit.setCellFactory(cellFactory);
    }
    
    public void kembali() throws IOException {
        App.setRoot("tugasPetugas");
    }
    
    private ObservableList<FeedsModel> studentsModels = new FeedsHandler().getFeeds();
    
}
