/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rplactpoint.databases;

import java.sql.*;
import com.mycompany.rplactpoint.utilities.SHA1Hash;
import com.mycompany.rplactpoint.databases.model.UserModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author deokr
 */
public class UserHandler extends connect {
    
    public UserHandler() {
        String sql = "CREATE TABLE IF NOT EXISTS user ("
                + "idUser INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "usernameUser TEXT NOT NULL,"
                + "passwordUser TEXT NOT NULL,"
                + "levelUser INTEGER NOT NULL"
                + ");";
        try {
            Statement stmt = super.getConn().createStatement();
            stmt.execute(sql);
        } catch(SQLException e) {
            System.out.print(e.getMessage());
        }
        
        UserModel admin = new UserModel("admin", new SHA1Hash("admin").getHasil(), 0);

        sql = "INSERT INTO user (usernameUser, passwordUser, levelUser) \n" + 
                "SELECT ?, ?, ? \n" +
                "WHERE NOT EXISTS(SELECT 1 FROM user WHERE usernameUser=? AND passwordUser=? AND levelUser=?);";
        
        try (PreparedStatement pstmt = super.getConn().prepareStatement(sql)) {
            pstmt.setString(1, admin.getUsernameUser());
            pstmt.setString(2, admin.getPasswordUser());
            pstmt.setInt(3, admin.getLevelUser());
            pstmt.setString(4, admin.getUsernameUser());
            pstmt.setString(5, admin.getPasswordUser());
            pstmt.setInt(6, admin.getLevelUser());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Success");
        
        UserModel mahasiswa;
        
        try (PreparedStatement pstmt = super.getConn().prepareStatement(sql)) {
            List<UserModel> users = readUserCSV();
            for (UserModel user : users) {
                pstmt.setString(1, user.getUsernameUser());
                pstmt.setString(2, user.getPasswordUser());
                pstmt.setInt(3, user.getLevelUser());
                pstmt.setString(4, user.getUsernameUser());
                pstmt.setString(5, user.getPasswordUser());
                pstmt.setInt(6, user.getLevelUser());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        MahasiswaHandler mahasiswaHandler = new MahasiswaHandler();
        System.out.println("Success");
    }
    
    private List<UserModel> readUserCSV() {
        List<UserModel> users = new ArrayList<>();
        Path pathToFile = Paths.get("./csv/mahasiswa.csv");
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                UserModel user = new UserModel(0, 0, 2, attributes[0], attributes[2]);
                users.add(user);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return users;
    }
    
    public UserModel getLogin(UserModel login) {        
        String sql = "SELECT * FROM user WHERE usernameUser = ? AND passwordUser = ?";
        
        try (PreparedStatement pstmt  = super.getConn().prepareStatement(sql)){
            pstmt.setString(1, login.getUsernameUser());
            pstmt.setString(2, login.getPasswordUser());
            ResultSet rs  = pstmt.executeQuery();
            
            if(rs == null) {
                return null;
            } else {
                int size = 0;
                UserModel user = null;
                while(rs.next()) {
                    if(size == 0) {
                        user = new UserModel(rs.getString("usernameUser"), rs.getString("passwordUser"), rs.getInt("levelUser"));
                    } else {
                        user = null;
                        break;
                    }
                    size++;
                }
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public UserModel getUser(UserModel login) {        
        String sql = "SELECT * FROM user WHERE usernameUser = ?";
        
        try (PreparedStatement pstmt  = super.getConn().prepareStatement(sql)){
            pstmt.setString(1, login.getUsernameUser());
            ResultSet rs  = pstmt.executeQuery();
            
            if(rs == null) {
                return null;
            } else {
                int size = 0;
                UserModel user = null;
                while(rs.next()) {
                    if(size == 0) {
                        user = new UserModel(rs.getString("usernameUser"), rs.getString("passwordUser"), rs.getInt("levelUser"));
                    } else {
                        user = new UserModel();
                        break;
                    }
                    size++;
                }
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void tambahUser(UserModel tambah) {
        String sql = "INSERT INTO user (usernameUser, passwordUser, levelUser) VALUES (?,?,?)";
        try (PreparedStatement pstmt = super.getConn().prepareStatement(sql)) {
            pstmt.setString(1, tambah.getUsernameUser());
            pstmt.setString(2, tambah.getPasswordUser());
            pstmt.setInt(3, tambah.getLevelUser());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void editPetugas(UserModel data, UserModel from) {
        String sql = "UPDATE user SET usernameUser = ?, passwordUser = ? WHERE usernameUser = ?";
        try (PreparedStatement pstmt = super.getConn().prepareStatement(sql)) {
            pstmt.setString(1, data.getUsernameUser());
            pstmt.setString(2, data.getPasswordUser());
            pstmt.setString(3, from.getUsernameUser());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void hapusPetugas(UserModel hapus) {
        String sql = "DELETE FROM user WHERE usernameUser = ?";
        
        try (PreparedStatement pstmt  = super.getConn().prepareStatement(sql)){
            pstmt.setString(1, hapus.getUsernameUser());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ObservableList<UserModel> getPetugas() {        
        String sql = "SELECT * FROM user WHERE levelUser = ?";
        
        try (PreparedStatement pstmt  = super.getConn().prepareStatement(sql)){
            pstmt.setInt(1, 1);
            ResultSet rs  = pstmt.executeQuery();
            
            if(rs == null) {
                return null;
            } else {
                List<UserModel> list = new ArrayList<>();
                int index = 1;
                while(rs.next()) {
                    System.out.println(rs.getString("usernameUser") + "  "+ rs.getString("passwordUser"));
                    list.add(new UserModel(index, rs.getInt("idUser"), rs.getString("usernameUser"), rs.getString("passwordUser")));
                    index++;
                }
                ObservableList<UserModel> petugas = FXCollections.observableList(list);
                return petugas;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
