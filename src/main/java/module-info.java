/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

module com.mycompany.rplactpoint {
    requires org.apache.commons.codec;
    requires commons.codec;
    requires sqlite.jdbc;
    requires javafx.controlsEmpty;
    requires javafx.controls;
    requires javafx.graphicsEmpty;
    requires javafx.graphics;
    requires javafx.baseEmpty;
    requires javafx.base;
    requires javafx.fxmlEmpty;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.mycompany.rplactpoint to javafx.fxml;
    opens com.mycompany.rplactpoint.databases.model to javafx.base;
    exports com.mycompany.rplactpoint;
}

