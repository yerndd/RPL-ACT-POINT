/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rplactpoint.databases.model;

/**
 *
 * @author deokr
 */
public class UserModel {
    private int index;
    private String usernameUser;
    private String passwordUser;
    private int levelUser;

    public UserModel() {
    }
    
    public UserModel(int index, String usernameUser, String passwordUser) {        
        this.index = index;
        this.usernameUser = usernameUser;
        this.passwordUser = passwordUser;
    } 

    public UserModel(String usernameUser, String passwordUser) {
        this.usernameUser = usernameUser;
        this.passwordUser = passwordUser;
    }    

    public UserModel(String usernameUser, String passwordUser, int levelUser) {
        this.usernameUser = usernameUser;
        this.passwordUser = passwordUser;
        this.levelUser = levelUser;
    }

    public String getUsernameUser() {
        return usernameUser;
    }

    public void setUsernameUser(String usernameUser) {
        this.usernameUser = usernameUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public int getLevelUser() {
        return levelUser;
    }

    public void setLevelUser(int levelUser) {
        this.levelUser = levelUser;
    }
    
    
}
