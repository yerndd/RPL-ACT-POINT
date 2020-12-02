/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rplactpoint.databases;

import java.sql.*;
import com.mycompany.rplactpoint.databases.model.PoinModel;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author deokr
 */
public class PoinHandler extends connect {
    
    public PoinHandler() {
        String sql = "CREATE TABLE IF NOT EXISTS poin ("
                + "idPoin INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nim TEXT NOT NULL,"
                + "nama TEXT NOT NULL,"
                + "jenisKegiatan TEXT NOT NULL,"
                + "tanggalKegiatan TEXT NOT NULL,"
                + "sebagaiKegiatan TEXT NOT NULL,"
                + "tingkatKegiatan TEXT NOT NULL,"
                + "namaKegiatan TEXT NOT NULL,"
                + "poinKegiatan INTEGER NOT NULL,"
                + "fotoSertif TEXT NOT NULL"
                + ");";
        try {
            Statement stmt = super.getConn().createStatement();
            stmt.execute(sql);
        } catch(SQLException e) {
            System.out.print(e.getMessage());
        }
        System.out.println("Success");
    }
    
    public PoinModel getPoin(PoinModel login) {        
        String sql = "SELECT * FROM poin WHERE nim = ? AND nama = ? AND jenisKegiatan = ? AND sebagaiKegiatan = ? AND tingkatKegiatan = ? AND namaKegiatan = ?";
        
        try (PreparedStatement pstmt  = super.getConn().prepareStatement(sql)){
            pstmt.setString(1, login.getNim());
            pstmt.setString(2, login.getNama());
            pstmt.setString(3, login.getJenisKegiatan());
            pstmt.setString(4, login.getSebagaiKegiatan());
            pstmt.setString(5, login.getTingkatKegiatan());
            pstmt.setString(6, login.getNamaKegiatan());
            ResultSet rs  = pstmt.executeQuery();
            
            if(rs == null) {
                return null;
            } else {
                int size = 0;
                PoinModel user = null;
                while(rs.next()) {
                    if(size == 0) {
                        user = new PoinModel(0, rs.getInt("idPoin"), rs.getString("nim"), rs.getString("nama"), rs.getString("tanggal"), rs.getString("tanggalKegiatan"), rs.getString("jenisKegiatan"), rs.getString("sebagaiKegiatan"), rs.getString("tingkatKegiatan"), rs.getString("namaKegiatan"), rs.getInt("poinKegiatan"), rs.getString("fotoSertif"));
                    } else {
                        user = new PoinModel();
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
    
    public void tambahRequest(PoinModel tambah) {
        String sql = "INSERT INTO poin (nim, nama, jenisKegiatan, tanggalKegiatan, sebagaiKegiatan, tingkatKegiatan, namaKegiatan, poinKegiatan, fotoSertif) VALUES (?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = super.getConn().prepareStatement(sql)) {
            pstmt.setString(1, tambah.getNim());
            pstmt.setString(2, tambah.getNama());
            pstmt.setString(3, tambah.getJenisKegiatan());
            pstmt.setString(4, tambah.getTanggalKegiatan());
            pstmt.setString(5, tambah.getSebagaiKegiatan());
            pstmt.setString(6, tambah.getTingkatKegiatan());
            pstmt.setString(7, tambah.getNamaKegiatan());
            pstmt.setInt(8, tambah.getPoinKegiatan());
            pstmt.setString(9, tambah.getFotoSertif());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
