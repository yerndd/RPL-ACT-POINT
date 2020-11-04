module com.mycompany.rplactpoint {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.rplactpoint to javafx.fxml;
    exports com.mycompany.rplactpoint;
}
