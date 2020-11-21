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
 * @author deokr
 */
public class ListPetugasController implements Initializable {

    @FXML Text loggedIn;
    @FXML TableView petugasTable;
    @FXML TableColumn<UserModel, Integer> idPetugas;
    @FXML TableColumn<UserModel, String> namaPetugas;
    @FXML TableColumn<UserModel, Void> editPetugas;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        loggedIn.setText(App.loggedIn.getUsernameUser());
        idPetugas.setCellValueFactory(new PropertyValueFactory<>("index"));
        namaPetugas.setCellValueFactory(new PropertyValueFactory<>("usernameUser"));
        petugasTable.setItems(studentsModels);
        
        Callback<TableColumn<UserModel, Void>, TableCell<UserModel, Void>> cellFactory = new Callback<TableColumn<UserModel, Void>, TableCell<UserModel, Void>>() {
            @Override
            public TableCell<UserModel, Void> call(final TableColumn<UserModel, Void> param) {
                final TableCell<UserModel, Void> cell = new TableCell<UserModel, Void>() {
                    private final Button btnEdit = new Button("Edit");
                    private final Button btnHapus = new Button("Hapus");
                    private final HBox box = new HBox();

                    {
                        btnEdit.setOnAction((ActionEvent event) -> {
                            UserModel data = getTableView().getItems().get(getIndex());
                            try {
                                App.userEdit = data;
                                App.setRoot("editPetugas");
                            } catch(IOException e) {
                                System.out.print(e.getMessage());
                            }
                        });
                        
                        btnHapus.setOnAction((ActionEvent event) -> {
                            UserModel data = getTableView().getItems().get(getIndex());
                            UserHandler tbUser = new UserHandler();
                            tbUser.hapusPetugas(data);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Berhasil");
                            alert.setHeaderText(null);
                            alert.setContentText("Petugas Berhasil dihapus");
                            alert.showAndWait();
                            try {
                                App.setRoot("listPetugas");
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
        editPetugas.setCellFactory(cellFactory);
    }
    
    public void kembali() throws IOException {
        App.setRoot("Admin");
    }
    
    private ObservableList<UserModel> studentsModels = new UserHandler().getPetugas();
    
}