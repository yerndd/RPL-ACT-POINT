/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rplactpoint.databases.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Me
 */
public class FeedsModel {
    private SimpleIntegerProperty index;
    private SimpleIntegerProperty idFeeds;
    private SimpleStringProperty judulKegiatan;
    private SimpleStringProperty deskripsiKegiatan;
    private SimpleStringProperty linkKegiatan;
  
    
    public FeedsModel() {
        this.index = null;
        this.idFeeds = null;
        this.judulKegiatan = null;
        this.deskripsiKegiatan = null;
        this.linkKegiatan = null;
    }
     
    public FeedsModel(int index, int id, String judul, String deskripsi, String link) {
        this.index = new SimpleIntegerProperty(index);
        this.idFeeds = new SimpleIntegerProperty(id);
        this.judulKegiatan = new SimpleStringProperty(judul);
        this.deskripsiKegiatan = new SimpleStringProperty(deskripsi);
        this.linkKegiatan = new SimpleStringProperty(link);
    }
    
     public FeedsModel(String judul, String deskripsi, String link) {
        this.index = null;
        this.idFeeds = null;
        this.judulKegiatan = new SimpleStringProperty(judul);
        this.deskripsiKegiatan = new SimpleStringProperty(deskripsi);
        this.linkKegiatan = new SimpleStringProperty(link);
    }

    
    public String getJudulKegiatan() {
        return judulKegiatan.get();
    }
        
    public int getIndex() {
        return index.get();
    }

    public void setIndex(SimpleIntegerProperty index) {
        this.index = index;
    }
           

    public String getDeskripsiKegiatan() {
        return deskripsiKegiatan.get();
    }
    

    public String getLinkKegiatan() {
        return linkKegiatan.get();
    }

    public void setJudulKegiatan(SimpleStringProperty judulKegiatan) {
        this.judulKegiatan = judulKegiatan;
    }

    public void setDeskripsiKegiatan(SimpleStringProperty deskripsiKegiatan) {
        this.deskripsiKegiatan = deskripsiKegiatan;
    }

    public void setLinkKegiatan(SimpleStringProperty linkKegiatan) {
        this.linkKegiatan = linkKegiatan;
    }
    
}
