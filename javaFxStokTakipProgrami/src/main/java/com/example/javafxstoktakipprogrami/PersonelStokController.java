package com.example.javafxstoktakipprogrami;


import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PersonelStokController extends yoneticiStokController{


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        urunNumarasiKolonu.setCellValueFactory(new PropertyValueFactory<>("numara"));
        isimKolonu.setCellValueFactory(new PropertyValueFactory<>("urunIsim"));
        adetKolonu.setCellValueFactory(new PropertyValueFactory<>("urunAdet"));
        alisFiyatiKolonu.setCellValueFactory(new PropertyValueFactory<>("urunAlis"));
        satisFiyatikolonu.setCellValueFactory(new PropertyValueFactory<>("urunSatis"));
        karKolonu.setCellValueFactory(new PropertyValueFactory<>("urunKar"));

        selectData dataStok=new selectData();
        urunlerTable.setItems(dataStok.selectStokData());

        urunNumarasiKolonu1.setCellValueFactory(new PropertyValueFactory<>("numara"));
        isimKolonu1.setCellValueFactory(new PropertyValueFactory<>("urunIsim"));
        adetKolonu1.setCellValueFactory(new PropertyValueFactory<>("urunAdet"));
        alisFiyatiKolonu1.setCellValueFactory(new PropertyValueFactory<>("urunAlis"));
        satisFiyatikolonu1.setCellValueFactory(new PropertyValueFactory<>("urunSatis"));
        karKolonu1.setCellValueFactory(new PropertyValueFactory<>("urunKar"));

        urunlerTable1.setItems(dataStok.selectStokData());
    }

    @Override
    public void refreshSatisAll() {
        super.refreshSatisAll();
    }



    @Override
    public void refreshAll() {
        super.refreshAll();
    }

    @Override
    public void anamenudon(ActionEvent event) throws IOException {
        super.anamenudon(event);
    }

    @Override
    public void satis() {
        super.satis();
    }

    @Override
    public void refreshSatis() {
        super.refreshSatis();
    }

    @Override
    public void tableSelected1() {
        super.tableSelected1();
    }

    @Override
    public void add() {
        super.add();
    }

    @Override
    public void refresh() {
        super.refresh();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void delete() {
        super.delete();
    }

    @Override
    public void tableSelected() {
        super.tableSelected();
    }

    @Override
    public void mouseBasıldı() {
        Button[] buttons={refreshButton,ekleButton,anamenuButon,deleteButton,updateButton,ekleButton1,refreshButton1};
        for (Button i:buttons){
            if (i.isPressed())
                i.setUnderline(true);
        }
    }

    @Override
    public void mouseKaldırıldı() {
        Button[] butonliste={refreshButton,ekleButton,anamenuButon,deleteButton,updateButton,ekleButton1,refreshButton1};
        for (Button i: butonliste){
            i.setUnderline(false);
        }
    }
}
