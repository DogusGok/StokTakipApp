package com.example.javafxstoktakipprogrami;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Personel {
    private SimpleIntegerProperty id;
    private SimpleStringProperty personelAd;
    private SimpleStringProperty sifre;
    private SimpleStringProperty calisanTipi;

    public Personel(int id, String personelAd, String sifre, String calisanTipi) {
        this.id =new SimpleIntegerProperty(id);;
        this.personelAd = new SimpleStringProperty(personelAd);
        this.sifre = new SimpleStringProperty(sifre);
        this.calisanTipi = new SimpleStringProperty(calisanTipi);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getPersonelAd() {
        return personelAd.get();
    }

    public SimpleStringProperty personelAdProperty() {
        return personelAd;
    }

    public void setPersonelAd(String personelAd) {
        this.personelAd.set(personelAd);
    }


    public String getSifre() {
        return sifre.get();
    }

    public SimpleStringProperty sifreProperty() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre.set(sifre);
    }

    public String getCalisanTipi() {
        return calisanTipi.get();
    }

    public SimpleStringProperty calisanTipiProperty() {
        return calisanTipi;
    }

    public void setCalisanTipi(String calisanTipi) {
        this.calisanTipi.set(calisanTipi);
    }
}
