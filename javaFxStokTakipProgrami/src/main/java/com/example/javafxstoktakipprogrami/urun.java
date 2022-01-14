package com.example.javafxstoktakipprogrami;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class urun {
    private SimpleIntegerProperty numara;
    private SimpleStringProperty urunIsim;
    private SimpleStringProperty urunAdet;
    private SimpleStringProperty urunAlis;
    private SimpleStringProperty urunSatis;
    private SimpleStringProperty urunKar;

    public urun(int num,String ad,String adet,String alis,String satis,String kar) {
        this.numara=new SimpleIntegerProperty(num);
        this.urunIsim=new SimpleStringProperty(ad);
        this.urunAdet=new SimpleStringProperty(adet);
        this.urunAlis=new SimpleStringProperty(alis);
        this.urunSatis=new SimpleStringProperty(satis);
        this.urunKar=new SimpleStringProperty(kar);
    }


    public int getNumara() {
        return numara.get();
    }

    public SimpleIntegerProperty numaraProperty() {
        return numara;
    }

    public void setNumara(int numara) {
        this.numara.set(numara);
    }

    public String getUrunIsim() {
        return urunIsim.get();
    }

    public SimpleStringProperty urunIsimProperty() {
        return urunIsim;
    }

    public void setUrunIsim(String urunIsim) {
        this.urunIsim.set(urunIsim);
    }

    public String getUrunAdet() {
        return urunAdet.get();
    }

    public SimpleStringProperty urunAdetProperty() {
        return urunAdet;
    }

    public void setUrunAdet(String urunAdet) {
        this.urunAdet.set(urunAdet);
    }

    public String getUrunAlis() {
        return urunAlis.get();
    }

    public SimpleStringProperty urunAlisProperty() {
        return urunAlis;
    }

    public void setUrunAlis(String urunAlis) {
        this.urunAlis.set(urunAlis);
    }

    public String getUrunSatis() {
        return urunSatis.get();
    }

    public SimpleStringProperty urunSatisProperty() {
        return urunSatis;
    }

    public void setUrunSatis(String urunSatis) {
        this.urunSatis.set(urunSatis);
    }

    public String getUrunKar() {
        return urunKar.get();
    }

    public SimpleStringProperty urunKarProperty() {
        return urunKar;
    }

    public void setUrunKar(String urunKar) {
        this.urunKar.set(urunKar);
    }
}
