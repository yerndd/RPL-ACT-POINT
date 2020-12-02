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
    private SimpleStringProperty nim;
    private SimpleStringProperty nama;
    private SimpleStringProperty tanggal;
    private SimpleStringProperty tanggalKegiatan;
    private SimpleStringProperty jenisKegiatan;
    private SimpleStringProperty sebagaiKegiatan;
    private SimpleStringProperty tingkatKegiatan;
    private SimpleStringProperty namaKegiatan;
    private SimpleStringProperty poinKegiatan;
    private SimpleStringProperty fotoSertif;

    public PoinModel() {
        this.index = null;
        this.idPoin = null;
        this.nim = null;
        this.nama = null;
        this.jenisKegiatan = null;
        this.sebagaiKegiatan = null;
        this.tingkatKegiatan = null;
        this.namaKegiatan = null;
        this.fotoSertif = null;
    }

    public PoinModel(int index, int idPoin, String nim, String nama, String jenisKegiatan, String sebagaiKegiatan, String tingkatKegiatan, String namaKegiatan, String fotoSertif) {
        this.index = new SimpleIntegerProperty(index);
        this.idPoin = new SimpleIntegerProperty(idPoin);
        this.nim = new SimpleStringProperty(nim);
        this.nama = new SimpleStringProperty(nama);
        this.jenisKegiatan = new SimpleStringProperty(jenisKegiatan);
        this.sebagaiKegiatan = new SimpleStringProperty(sebagaiKegiatan);
        this.tingkatKegiatan = new SimpleStringProperty(tingkatKegiatan);
        this.namaKegiatan = new SimpleStringProperty(namaKegiatan);
        this.fotoSertif = new SimpleStringProperty(fotoSertif);
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

    public String getPoinKegiatan() {
        return poinKegiatan.get();
    }

    public void setPoinKegiatan(String poinKegiatan) {
        this.poinKegiatan = new SimpleStringProperty(poinKegiatan);
    }

    public String getFotoSertif() {
        return fotoSertif.get();
    }

    public void setFotoSertif(String fotoSertif) {
        this.fotoSertif = new SimpleStringProperty(fotoSertif);
    }
    
    
}
