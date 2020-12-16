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
 * @author User
 */
public class PoinModel {
    
    private SimpleIntegerProperty index;
    private SimpleIntegerProperty idPoin;
    private MahasiswaModel mahasiswa;
    private SimpleStringProperty tanggal;
    private SimpleStringProperty tanggalKegiatan;
    private SimpleStringProperty jenisKegiatan;
    private SimpleStringProperty sebagaiKegiatan;
    private SimpleStringProperty tingkatKegiatan;
    private SimpleStringProperty namaKegiatan;
    private SimpleIntegerProperty poinKegiatan;
    private SimpleStringProperty fotoSertif;

    public PoinModel() {
        this.index = null;
        this.idPoin = null;
        this.mahasiswa = null;
        this.tanggal = null;
        this.tanggalKegiatan = null;
        this.jenisKegiatan = null;
        this.sebagaiKegiatan = null;
        this.tingkatKegiatan = null;
        this.namaKegiatan = null;
        this.poinKegiatan = null;
        this.fotoSertif = null;
    }

    public PoinModel(int index, int idPoin, MahasiswaModel mahasiswa, String tanggal, String tanggalKegiatan, String jenisKegiatan, String sebagaiKegiatan, String tingkatKegiatan, String namaKegiatan, int poinKegiatan, String fotoSertif) {
        this.index = new SimpleIntegerProperty(index);
        this.idPoin = new SimpleIntegerProperty(idPoin);
        this.mahasiswa = mahasiswa;
        this.tanggal = new SimpleStringProperty(tanggal);
        this.tanggalKegiatan = new SimpleStringProperty(tanggalKegiatan);
        this.jenisKegiatan = new SimpleStringProperty(jenisKegiatan);
        this.sebagaiKegiatan = new SimpleStringProperty(sebagaiKegiatan);
        this.tingkatKegiatan = new SimpleStringProperty(tingkatKegiatan);
        this.namaKegiatan = new SimpleStringProperty(namaKegiatan);
        this.poinKegiatan = new SimpleIntegerProperty(poinKegiatan);
        this.fotoSertif = new SimpleStringProperty(fotoSertif);
    }
    
    public PoinModel(int index, int idPoin, MahasiswaModel mahasiswa, String nama, String tanggalKegiatan, String jenisKegiatan, String sebagaiKegiatan, String tingkatKegiatan, String namaKegiatan, int poinKegiatan) {
        this.index = new SimpleIntegerProperty(index);
        this.idPoin = new SimpleIntegerProperty(idPoin);
        this.mahasiswa = mahasiswa;
        this.tanggalKegiatan = new SimpleStringProperty(tanggalKegiatan);
        this.jenisKegiatan = new SimpleStringProperty(jenisKegiatan);
        this.sebagaiKegiatan = new SimpleStringProperty(sebagaiKegiatan);
        this.tingkatKegiatan = new SimpleStringProperty(tingkatKegiatan);
        this.namaKegiatan = new SimpleStringProperty(namaKegiatan);
        this.poinKegiatan = new SimpleIntegerProperty(poinKegiatan);
    }
    
    public int getIndex() {
        return index.get();
    }

    public void setIndex(int index) {
        this.index = new SimpleIntegerProperty(index);
    }

    public int getIdPoin() {
        return idPoin.get();
    }

    public void setIdPoin(int idPoin) {
        this.idPoin = new SimpleIntegerProperty(idPoin);
    }

    public String getTanggal() {
        return tanggal.get();
    }

    public void setTanggal(String tanggal) {
        this.tanggal = new SimpleStringProperty(tanggal);
    }

    public String getTanggalKegiatan() {
        return tanggalKegiatan.get();
    }

    public void setTanggalKegiatan(String tanggalKegiatan) {
        this.tanggalKegiatan = new SimpleStringProperty(tanggalKegiatan);
    }

    public MahasiswaModel getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(MahasiswaModel mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public String getJenisKegiatan() {
        return jenisKegiatan.get();
    }

    public void setJenisKegiatan(String jenisKegiatan) {
        this.jenisKegiatan = new SimpleStringProperty(jenisKegiatan);
    }

    public String getSebagaiKegiatan() {
        return sebagaiKegiatan.get();
    }

    public void setSebagaiKegiatan(String sebagaiKegiatan) {
        this.sebagaiKegiatan = new SimpleStringProperty(sebagaiKegiatan);
    }

    public String getTingkatKegiatan() {
        return tingkatKegiatan.get();
    }

    public void setTingkatKegiatan(String tingkatKegiatan) {
        this.tingkatKegiatan = new SimpleStringProperty(tingkatKegiatan);
    }

    public String getNamaKegiatan() {
        return namaKegiatan.get();
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = new SimpleStringProperty(namaKegiatan);
    }

    public int getPoinKegiatan() {
        return poinKegiatan.get();
    }

    public void setPoinKegiatan(int poinKegiatan) {
        this.poinKegiatan = new SimpleIntegerProperty(poinKegiatan);
    }

    public String getFotoSertif() {
        return fotoSertif.get();
    }

    public void setFotoSertif(String fotoSertif) {
        this.fotoSertif = new SimpleStringProperty(fotoSertif);
    }
    
    
}
