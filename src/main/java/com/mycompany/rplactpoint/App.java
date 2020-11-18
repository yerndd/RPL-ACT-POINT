package com.mycompany.rplactpoint;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import com.mycompany.rplactpoint.databases.model.UserModel;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private TableView table = new TableView();
    public static UserModel loggedIn;
    public static UserModel userEdit;
    
    @FXML private TableView<UserModel> tableView;
    @FXML private TableColumn<UserModel, String> usernameColumn;
    @FXML private TableColumn<UserModel, String> passwordColumn;
    @FXML private TableColumn<UserModel, Integer> levelColumn;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"));
        stage.setScene(scene);
        stage.show();
    }
    
    public static void setRoot(String page) throws IOException {
        scene.setRoot(loadFXML(page));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}