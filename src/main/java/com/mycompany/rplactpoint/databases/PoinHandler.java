/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rplactpoint.databases;

import com.mycompany.rplactpoint.databases.model.MahasiswaModel;
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
        String sql = "SELECT * FROM poin WHERE nim = ? AND jenisKegiatan = ? AND sebagaiKegiatan = ? AND tingkatKegiatan = ? AND namaKegiatan = ?";
        
        try (PreparedStatement pstmt  = super.getConn().prepareStatement(sql)){
            pstmt.setString(1, login.getMahasiswa().getNim());
            pstmt.setString(2, login.getJenisKegiatan());
            pstmt.setString(3, login.getSebagaiKegiatan());
            pstmt.setString(4, login.getTingkatKegiatan());
            pstmt.setString(5, login.getNamaKegiatan());
            ResultSet rs  = pstmt.executeQuery();
            
            if(rs == null) {
                return null;
            } else {
                int size = 0;
                PoinModel user = null;
                while(rs.next()) {
                    if(size == 0) {
                        user = new PoinModel(0, rs.getInt("idPoin"), login.getMahasiswa(), rs.getString("tanggal"), rs.getString("tanggalKegiatan"), rs.getString("jenisKegiatan"), rs.getString("sebagaiKegiatan"), rs.getString("tingkatKegiatan"), rs.getString("namaKegiatan"), rs.getInt("poinKegiatan"), rs.getString("fotoSertif"));
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
        String sql = "INSERT INTO poin (nim, jenisKegiatan, tanggalKegiatan, sebagaiKegiatan, tingkatKegiatan, namaKegiatan, poinKegiatan, fotoSertif) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = super.getConn().prepareStatement(sql)) {
            pstmt.setString(1, tambah.getMahasiswa().getNim());
            pstmt.setString(2, tambah.getJenisKegiatan());
            pstmt.setString(3, tambah.getTanggalKegiatan());
            pstmt.setString(4, tambah.getSebagaiKegiatan());
            pstmt.setString(5, tambah.getTingkatKegiatan());
            pstmt.setString(6, tambah.getNamaKegiatan());
            pstmt.setInt(7, tambah.getPoinKegiatan());
            pstmt.setString(8, tambah.getFotoSertif());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ObservableList<PoinModel> getHistory(String nim) {        
        String sql = "SELECT * FROM poin, mahasiswa WHERE poin.nim=mahasiswa.nim AND mahasiswa.nim='"+nim+"'";
        
        try (PreparedStatement pstmt  = super.getConn().prepareStatement(sql)){
            ResultSet rs  = pstmt.executeQuery();
            
            if(rs == null) {
                return null;
            } else {
                List<PoinModel> list = new ArrayList<>();
                int index = 1;
                while(rs.next()) {
                    PoinModel temp = new PoinModel();
                    MahasiswaModel tempMah = new MahasiswaModel(0, rs.getInt("idMahasiswa"), rs.getString("nim"), rs.getString("nama"), rs.getInt("totalPoin"), rs.getString("foto"));
                    temp.setIndex(index);
                    temp.setMahasiswa(tempMah);  
                    temp.setTanggalKegiatan(rs.getString("tanggalKegiatan"));
                    temp.setJenisKegiatan(rs.getString("jenisKegiatan"));
                    temp.setNamaKegiatan(rs.getString("namaKegiatan"));
                    temp.setTingkatKegiatan(rs.getString("tingkatKegiatan"));
                    temp.setPoinKegiatan(rs.getInt("poinKegiatan"));
                                   
                    list.add(temp);
                    
                    index++;
                }
               ObservableList<PoinModel> poin = FXCollections.observableList(list);
               return poin;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public ObservableList<PoinModel> getLaporan() {        
        String sql = "SELECT * FROM poin, mahasiswa WHERE poin.nim=mahasiswa.nim";
        
        try (PreparedStatement pstmt  = super.getConn().prepareStatement(sql)){
            ResultSet rs  = pstmt.executeQuery();
            
            if(rs == null) {
                return null;
            } else {
                List<PoinModel> list = new ArrayList<>();
                int index = 1;
                while(rs.next()) {
                    PoinModel temp = new PoinModel();
                    MahasiswaModel tempMah = new MahasiswaModel(0, rs.getInt("idMahasiswa"), rs.getString("nim"), rs.getString("nama"), rs.getInt("totalPoin"), rs.getString("foto"));
                    temp.setIndex(index); 
                    temp.setMahasiswa(tempMah);
                    temp.setTanggalKegiatan(rs.getString("tanggalKegiatan"));
                    temp.setJenisKegiatan(rs.getString("jenisKegiatan"));
                    temp.setNamaKegiatan(rs.getString("namaKegiatan"));
                    temp.setTingkatKegiatan(rs.getString("tingkatKegiatan"));
                    temp.setPoinKegiatan(rs.getInt("poinKegiatan"));
                                   
                    list.add(temp);
                    
                    index++;
                }
               ObservableList<PoinModel> poin = FXCollections.observableList(list);
               return poin;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
       
      
}
