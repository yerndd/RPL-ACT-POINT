/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rplactpoint.databases;

import com.mycompany.rplactpoint.databases.model.MahasiswaModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author deokr
 */
public class MahasiswaHandler extends connect {
    public MahasiswaHandler() {
        String sql = "CREATE TABLE IF NOT EXISTS mahasiswa ("
                + "idMahasiswa INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nim TEXT NOT NULL,"
                + "nama TEXT NOT NULL,"
                + "totalPoin INTEGER NOT NULL,"
                + "foto TEXT NOT NULL"
                + ");";
        try {
            Statement stmt = super.getConn().createStatement();
            stmt.execute(sql);
        } catch(SQLException e) {
            System.out.print(e.getMessage());
        }
        
        sql = "INSERT INTO mahasiswa (nim, nama, totalPoin, foto) \n" + 
                "SELECT ?, ?, ?, ? \n" +
                "WHERE NOT EXISTS(SELECT 1 FROM mahasiswa WHERE nim=? AND nama=?);";
        
        MahasiswaModel mahasiswa;
        
        try (PreparedStatement pstmt = super.getConn().prepareStatement(sql)) {
            List<MahasiswaModel> users = readMahasiswaCSV();
            for (MahasiswaModel user : users) {
                pstmt.setString(1, user.getNim());
                pstmt.setString(2, user.getNama());
                pstmt.setInt(3, user.getTotalPoin());
                pstmt.setString(4, user.getFoto());
                pstmt.setString(5, user.getNim());
                pstmt.setString(6, user.getNama());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Success");
    }
    
    private List<MahasiswaModel> readMahasiswaCSV() {
        List<MahasiswaModel> users = new ArrayList<>();
        Path pathToFile = Paths.get("./csv/mahasiswa.csv");
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                MahasiswaModel user = new MahasiswaModel(0, 0, attributes[0], attributes[1], 0, "");
                users.add(user);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return users;
    }
    
    public MahasiswaModel getMahasiswaNim(MahasiswaModel login) {        
        String sql = "SELECT * FROM mahasiswa WHERE nim = ?";
        
        try (PreparedStatement pstmt  = super.getConn().prepareStatement(sql)){
            pstmt.setString(1, login.getNim());
            ResultSet rs  = pstmt.executeQuery();
            
            if(rs == null) {
                return null;
            } else {
                int size = 0;
                MahasiswaModel user = null;
                while(rs.next()) {
                    if(size == 0) {
                        user = new MahasiswaModel(0, rs.getInt("idMahasiswa"), rs.getString("nim"), rs.getString("nama"), rs.getInt("totalPoin"), rs.getString("foto"));
                    } else {
                        user = new MahasiswaModel();
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
}
