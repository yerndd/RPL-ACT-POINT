/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rplactpoint.databases;

import java.sql.*;
import com.mycompany.rplactpoint.databases.model.FeedsModel;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Me
 */
public class FeedsHandler extends connect {
    public FeedsHandler() {
        String sql = "CREATE TABLE IF NOT EXISTS feeds ("
                + "idFeeds INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "judulKegiatan TEXT NOT NULL,"
                + "deskripsiKegiatan TEXT NOT NULL,"
                + "linkKegiatan TEXT NOT NULL"
                + ");";
        try {
            Statement stmt = super.getConn().createStatement();
            stmt.execute(sql);
        } catch(SQLException e) {
            System.out.print(e.getMessage());
        }
        System.out.println("Success");
    }
    
    public FeedsModel getJudulKegiatan(FeedsModel login) {        
        String sql = "SELECT * FROM feeds WHERE judulKegiatan = ?";
        
        try (PreparedStatement pstmt  = super.getConn().prepareStatement(sql)){
            pstmt.setString(1, login.getJudulKegiatan());
            ResultSet rs  = pstmt.executeQuery();
            
            if(rs == null) {
                return null;
            } else {
                int size = 0;
                FeedsModel user = null;
                while(rs.next()) {
                    if(size == 0) {
                        user = new FeedsModel(rs.getString("judulKegiatan"), rs.getString("deskripsiKegiatan"), rs.getString("linkKegiatan"));
                    } else {
                        user = new FeedsModel();
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
        
    public ObservableList<FeedsModel> getFeeds() {        
        String sql = "SELECT * FROM feeds";
        
        try (PreparedStatement pstmt  = super.getConn().prepareStatement(sql)){
            ResultSet rs  = pstmt.executeQuery();
            
            if(rs == null) {
                return null;
            } else {
                List<FeedsModel> list = new ArrayList<>();
                int index = 1;
                while(rs.next()) {
                    list.add(new FeedsModel(index, rs.getInt("idFeeds"), rs.getString("judulKegiatan"), rs.getString("deskripsiKegiatan"), rs.getString("linkKegiatan")));
                    index++;
                }
                ObservableList<FeedsModel> feeds = FXCollections.observableList(list);
                return feeds;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public void tambahFeeds(FeedsModel tambah) {
        String sql = "INSERT INTO feeds (judulKegiatan, deskripsiKegiatan, linkKegiatan) VALUES (?,?,?)";
        try (PreparedStatement pstmt = super.getConn().prepareStatement(sql)) {
            pstmt.setString(1, tambah.getJudulKegiatan());
            pstmt.setString(2, tambah.getDeskripsiKegiatan());
            pstmt.setString(3, tambah.getLinkKegiatan());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void editFeeds(FeedsModel data, FeedsModel from) {
        String sql = "UPDATE feeds SET judulKegiatan = ?, deskripsiKegiatan = ?, linkKegiatan = ? WHERE judulKegiatan = ?";
        try (PreparedStatement pstmt = super.getConn().prepareStatement(sql)) {
            pstmt.setString(1, data.getJudulKegiatan());
            pstmt.setString(2, data.getDeskripsiKegiatan());
            pstmt.setString(3, from.getLinkKegiatan());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void hapusFeeds(FeedsModel hapus) {
        String sql = "DELETE FROM feeds WHERE judulKegiatan = ?";
        
        try (PreparedStatement pstmt  = super.getConn().prepareStatement(sql)){
            pstmt.setString(1, hapus.getJudulKegiatan());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        
}
