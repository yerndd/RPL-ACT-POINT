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
public class MahasiswaModel {
    
    private SimpleIntegerProperty index;
    private SimpleIntegerProperty idMahasiswa;
    private SimpleStringProperty nim;
    private SimpleStringProperty nama;
    private SimpleIntegerProperty totalPoin;
    private SimpleStringProperty foto;

    public MahasiswaModel() {
    }

    public MahasiswaModel(int index, int idMahasiswa, String nim, String nama, int totalPoin, String foto) {
        this.index = new SimpleIntegerProperty(index);
        this.idMahasiswa = new SimpleIntegerProperty(idMahasiswa);
        this.nim = new SimpleStringProperty(nim);
        this.nama = new SimpleStringProperty(nama);
        this.totalPoin = new SimpleIntegerProperty(totalPoin);
        this.foto = new SimpleStringProperty(foto);
    }

    public int getIndex() {
        return index.get();
    }

    public void setIndex(int index) {
        this.index = new SimpleIntegerProperty(index);
    }

    public int getIdMahasiswa() {
        return idMahasiswa.get();
    }

    public void setIdMahasiswa(int idMahasiswa) {
        this.idMahasiswa = new SimpleIntegerProperty(idMahasiswa);
    }

    public String getNim() {
        return nim.get();
    }

    public void setNim(String nim) {
        this.nim = new SimpleStringProperty(nim);
    }

    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama = new SimpleStringProperty(nama);
    }

    public int getTotalPoin() {
        return totalPoin.get();
    }

    public void setTotalPoin(int totalPoin) {
        this.totalPoin = new SimpleIntegerProperty(totalPoin);
    }

    public String getFoto() {
        return foto.get();
    }

    public void setFoto(String foto) {
        this.foto = new SimpleStringProperty(foto);
    }    
}
