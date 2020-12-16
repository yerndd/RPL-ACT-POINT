/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rplactpoint.databases.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author deokr
 */
public class UserModel {
    private SimpleIntegerProperty index;
    private SimpleIntegerProperty idUser;
    private SimpleIntegerProperty levelUser;
    private SimpleStringProperty usernameUser;
    private SimpleStringProperty passwordUser;

    public UserModel() {
        this.index = null;
        this.idUser = null;
        this.levelUser = null;
        this.usernameUser = null;
        this.passwordUser = null;
    }

    public UserModel(String usernameUser, String passwordUser) {
        this.index = null;
        this.idUser = null;
        this.levelUser = null;
        this.usernameUser = new SimpleStringProperty(usernameUser);
        this.passwordUser = new SimpleStringProperty(passwordUser);
    }

    public UserModel(int idUser, String usernameUser, String passwordUser) {
        this.index = null;
        this.idUser = new SimpleIntegerProperty(idUser);
        this.levelUser = null;
        this.usernameUser = new SimpleStringProperty(usernameUser);
        this.passwordUser = new SimpleStringProperty(passwordUser);
    }
    
    public UserModel(int index, int idUser, String usernameUser, String passwordUser) {
        this.index = new SimpleIntegerProperty(index);
        this.idUser = new SimpleIntegerProperty(idUser);
        this.levelUser = null;
        this.usernameUser = new SimpleStringProperty(usernameUser);
        this.passwordUser = new SimpleStringProperty(passwordUser);
    }

    public UserModel(String usernameUser, String passwordUser, int levelUser) {
        this.index = null;
        this.idUser = null;
        this.usernameUser = new SimpleStringProperty(usernameUser);
        this.passwordUser = new SimpleStringProperty(passwordUser);
        this.levelUser = new SimpleIntegerProperty(levelUser);
    }
    
    public UserModel(int index, int idUser, int levelUser, String usernameUser, String passwordUser) {
        this.index = new SimpleIntegerProperty(index);
        this.idUser = new SimpleIntegerProperty(idUser);
        this.levelUser = new SimpleIntegerProperty(levelUser);
        this.usernameUser = new SimpleStringProperty(usernameUser);
        this.passwordUser = new SimpleStringProperty(passwordUser);
    }

    public int getIndex() {
        return index.get();
    }

    public void setIndex(int index) {
        this.index = new SimpleIntegerProperty(index);
    }

    public int getIdUser() {
        return idUser.get();
    }

    public void setIdUser(int idUser) {
        this.idUser = new SimpleIntegerProperty(idUser);
    }

    public int getLevelUser() {
        return levelUser.get();
    }

    public void setLevelUser(int levelUser) {
        this.levelUser = new SimpleIntegerProperty(levelUser);
    }

    public String getUsernameUser() {
        return usernameUser.get();
    }

    public void setUsernameUser(String usernameUser) {
        this.usernameUser = new SimpleStringProperty(usernameUser);
    }

    public String getPasswordUser() {
        return passwordUser.get();
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = new SimpleStringProperty(passwordUser);
    }
}
