package com.example.javafxstoktakipprogrami;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class yoneticiStokController  implements Initializable  {


    @FXML
    public TableView<urun> urunlerTable,urunlerTable1;
    public TableColumn<urun,String> urunNumarasiKolonu,urunNumarasiKolonu1;
    public TableColumn<urun,String> isimKolonu,isimKolonu1;
    public TableColumn<urun,String> adetKolonu,adetKolonu1;
    public TableColumn<urun,String> alisFiyatiKolonu,alisFiyatiKolonu1;
    public TableColumn<urun,String> satisFiyatikolonu,satisFiyatikolonu1;
    public TableColumn<urun,String> karKolonu,karKolonu1;



    public TableView<Personel> personellerTable;
    public TableColumn<Personel,Integer> personelIdColoumn;
    public TableColumn<Personel,String> personelAdColoumn;
    public TableColumn<Personel,String> sifreColoumn;
    public TableColumn<Personel,String> calisanTipiColoumn;




    public Button personelRefreshButton;
    public Button ekleButton;
    public Button personelEkleButton;
    public Button updateButton;
    public Button deleteButton;
    public Button updatePersonelButton;
    public Button deletePersonelButton;
    public Button refreshButton;
    public Button anamenuButon;

    public AnchorPane pane;

    public TextField urunAdTf;
    public TextField urunAdetTf;
    public TextField alisFiyatiTf;
    public TextField satisFiyatiTf;
    public TextField personelKullaniciAdTf;
    public TextField sifreTf;


    public Label uyariLabel;
    public Label uyariLabel1;
    public Label urunNumaras??Label;
    public Label ??r??nNoTextLabel;
    public Label idLabel;
    public Label idTextLabel;
    public Label mainTitleLabel;



    public ChoiceBox choiceBox;


    private final String[] calisanTipi={"Y??netici","Personel"};
    public Label uyariLabel2;
    public TextField urunAdTf1;

    public Button ekleButton1;
    public Button refreshButton1;
    public TextField urunAdetTf1;
    public TextField alisFiyatiTf1;
    public TextField satisFiyatiTf1;
    public Label urunNumaras??Label1;
    public Label ??r??nNoTextLabel1;
    public TextField sat??lanAdetTf;


    NumberFormat nf=NumberFormat.getInstance();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        urunNumarasiKolonu.setCellValueFactory(new PropertyValueFactory<>("Numara"));
        isimKolonu.setCellValueFactory(new PropertyValueFactory<>("UrunIsim"));
        adetKolonu.setCellValueFactory(new PropertyValueFactory<>("UrunAdet"));
        alisFiyatiKolonu.setCellValueFactory(new PropertyValueFactory<>("UrunAlis"));
        satisFiyatikolonu.setCellValueFactory(new PropertyValueFactory<>("UrunSatis"));
        karKolonu.setCellValueFactory(new PropertyValueFactory<>("UrunKar"));

        selectData selectData= new selectData();
        urunlerTable.setItems(selectData.selectStokData());



        personelIdColoumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        personelAdColoumn.setCellValueFactory(new PropertyValueFactory<>("personelAd"));
        sifreColoumn.setCellValueFactory(new PropertyValueFactory<>("sifre"));
        calisanTipiColoumn.setCellValueFactory(new PropertyValueFactory<>("calisanTipi"));

        selectData dataPersonel=new selectData();
        personellerTable.setItems(dataPersonel.selectPersonelData());

        choiceBox.getItems().addAll(calisanTipi);


        urunNumarasiKolonu1.setCellValueFactory(new PropertyValueFactory<>("numara"));
        isimKolonu1.setCellValueFactory(new PropertyValueFactory<>("urunIsim"));
        adetKolonu1.setCellValueFactory(new PropertyValueFactory<>("urunAdet"));
        alisFiyatiKolonu1.setCellValueFactory(new PropertyValueFactory<>("urunAlis"));
        satisFiyatikolonu1.setCellValueFactory(new PropertyValueFactory<>("urunSatis"));
        karKolonu1.setCellValueFactory(new PropertyValueFactory<>("urunKar"));

        urunlerTable1.setItems(selectData.selectStokData());


    }


    public void add(){
        urunNumaras??Label.setText("");
        ??r??nNoTextLabel.setVisible(false);

        TextField[] textFields={alisFiyatiTf,satisFiyatiTf,urunAdTf,urunAdetTf};

        if (alisFiyatiTf.getText().isEmpty() || satisFiyatiTf.getText().isEmpty() || urunAdetTf.getText().isEmpty() || urunAdetTf.getText().isEmpty() ){

            for (TextField t:textFields){

                if (t.getText().isEmpty())
                    t.setPromptText("l??tfen de??er giriniz");
            }
            uyariLabel.setTextFill(Color.RED);
            uyariLabel.setText("L??tfen bo?? yer b??rakmay??n!");

        }else {

            if(Integer.parseInt(urunAdetTf.getText())<=0){
                uyariLabel.setTextFill(Color.RED);
                uyariLabel.setText("??r??n Adedi '0' veya daha az olamaz!");

            }else {
                uyariLabel.setTextFill(Color.valueOf("#019710"));
                double karDouble = (100 * (Double.parseDouble(satisFiyatiTf.getText()) - Double.parseDouble(alisFiyatiTf.getText()))) / Double.parseDouble(alisFiyatiTf.getText());
                nf.setMaximumFractionDigits(1);
                String kar = nf.format(karDouble);

                insertData insertData = new insertData("urunler.mevcut_urunler", "urun_adi", "urun_adedi", "alis_fiyati", "satis_fiyati", "kar_yuzde", urunAdTf, urunAdetTf, alisFiyatiTf, satisFiyatiTf, kar);
                uyariLabel.setText("??r??n Kaydedildi!");
                refresh();
            }
        }
        for (TextField t:textFields){
            t.clear();
        }


    }


    public void refresh(){


        TextField[] textFields={alisFiyatiTf,satisFiyatiTf,urunAdTf,urunAdetTf};
        for (TextField t:textFields){
            t.clear();}

            urunNumaras??Label.setText("");
            ??r??nNoTextLabel.setVisible(false);

            urunlerTable.getItems().clear();

            urunNumarasiKolonu.setCellValueFactory(new PropertyValueFactory<>("numara"));
            isimKolonu.setCellValueFactory(new PropertyValueFactory<>("urunIsim"));
            adetKolonu.setCellValueFactory(new PropertyValueFactory<>("urunAdet"));
            alisFiyatiKolonu.setCellValueFactory(new PropertyValueFactory<>("urunAlis"));
            satisFiyatikolonu.setCellValueFactory(new PropertyValueFactory<>("urunSatis"));
            karKolonu.setCellValueFactory(new PropertyValueFactory<>("urunKar"));
            selectData data=new selectData();
            urunlerTable.setItems(data.selectStokData());



    }
    public void refreshAll(){
        refresh();
        uyariLabel.setText("");
    }



    public void update(){

        TextField[] textFields={urunAdTf,urunAdetTf,alisFiyatiTf,satisFiyatiTf};
        if (urunAdTf.getText().isEmpty() || urunAdetTf.getText().isEmpty() || alisFiyatiTf.getText().isEmpty() || satisFiyatiTf.getText().isEmpty() ){
            for (TextField t:textFields){
                if (t.getText().isEmpty())
                    t.setPromptText("l??tfen de??er giriniz");
            }
            uyariLabel.setTextFill(Color.RED);
            uyariLabel.setText("L??tfen bo?? yer b??rakmay??n!");
        }else if (urunlerTable.getSelectionModel().isEmpty()){
            uyariLabel.setTextFill(Color.RED);
            uyariLabel.setText("L??tfen tablodan ??r??n se??iniz!");
        }
        else {


            double karDouble=(100*(Double.parseDouble(satisFiyatiTf.getText())-Double.parseDouble(alisFiyatiTf.getText())))/Double.parseDouble(alisFiyatiTf.getText());
            nf.setMaximumFractionDigits(1);
            String kar=nf.format(karDouble);
            if(Integer.parseInt(urunAdetTf.getText())<=0){
                uyariLabel.setTextFill(Color.RED);
                uyariLabel.setText("??r??n Adedi '0' veya daha az olamaz!");

            }else {
                editData editData = new editData("urunler.mevcut_urunler", "urun_adi", "urun_adedi", "alis_fiyati", "satis_fiyati", "kar_yuzde", "urun_numarasi", urunAdTf, urunAdetTf, alisFiyatiTf, satisFiyatiTf, kar, urunNumaras??Label.getText());
                uyariLabel.setTextFill(Color.valueOf("#019710"));
                uyariLabel.setText("??r??n D??zenlendi!");
            }

            for (TextField t:textFields){
                t.clear();
            }
            urunNumaras??Label.setText("");
            ??r??nNoTextLabel.setVisible(false);
            refresh();


        }


    }


    public void delete(){

        TextField[] textFields={urunAdTf,urunAdetTf,alisFiyatiTf,satisFiyatiTf};
        if (urunAdTf.getText().isEmpty() || urunAdetTf.getText().isEmpty() || alisFiyatiTf.getText().isEmpty() || satisFiyatiTf.getText().isEmpty()){
            for (TextField t:textFields){
                if (t.getText().isEmpty())
                    t.setPromptText("l??tfen de??er giriniz");
            }
            uyariLabel.setTextFill(Color.RED);
            uyariLabel.setText("L??tfen bo?? yer b??rakmay??n!");
        }else if (urunlerTable.getSelectionModel().isEmpty()){
            uyariLabel.setTextFill(Color.RED);
            uyariLabel.setText("L??tfen tablodan ??r??n se??iniz!");
        }
        else {


            deleteData deleteData=new deleteData("urunler.mevcut_urunler","urun_numarasi",urunNumaras??Label.getText());
            uyariLabel.setTextFill(Color.valueOf("#019710"));
            uyariLabel.setText("??r??n Silindi!");


            for (TextField t:textFields){
                t.clear();
            }
            refresh();


        }


    }


    public void tableSelected(){



        try {
            uyariLabel.setText("");
            urun selected=urunlerTable.getSelectionModel().getSelectedItem();
            ??r??nNoTextLabel.setVisible(true);
            urunNumaras??Label.setText(String.valueOf(selected.getNumara()));
            urunAdTf.setText(selected.getUrunIsim());
            urunAdetTf.setText(String.valueOf(selected.getUrunAdet()));
            alisFiyatiTf.setText(String.valueOf(selected.getUrunAlis()));
            satisFiyatiTf.setText(String.valueOf(selected.getUrunSatis()));
        }catch (NullPointerException e) {
            System.out.println("tablodan eleman se??ilmedi");

        }
    }




    public void addPersonel(ActionEvent event){
        TextField[] textFields={personelKullaniciAdTf,sifreTf};
        idLabel.setText("");
        idTextLabel.setVisible(false);



        if (personelKullaniciAdTf.getText().isEmpty() || sifreTf.getText().isEmpty() ){

            for (TextField t:textFields){
                if (t.getText().isEmpty())
                    t.setPromptText("l??tfen de??er giriniz");
            }

            uyariLabel1.setTextFill(Color.RED);
            uyariLabel1.setText("L??tfen bo?? yer b??rakmay??n!");
        }else {

            String sql="SELECT count(1) FROM kullanicilar.kullanicibilgileri WHERE Kullanici_Adi='"+personelKullaniciAdTf.getText()+"'" ;

            try {

                Connection connect = DbHelper.getConnection();
                PreparedStatement preparedStatement = connect.prepareStatement(sql);
                ResultSet resultSet=preparedStatement.executeQuery(sql);

                while (resultSet.next()){

                    if (resultSet.getInt(1)==1){

                        uyariLabel1.setText("Bu Kullan??c?? Ad?? Al??nm????!");
                        uyariLabel1.setTextFill(Color.RED);

                    }else {

                        insertData insertData=new insertData("kullanicilar.kullanicibilgileri","Kullanici_Adi","Sifre","Calisan_Tipi",personelKullaniciAdTf,sifreTf,choiceBox);
                        uyariLabel1.setTextFill(Color.valueOf("#019710"));
                        uyariLabel1.setText("Kayd??n??z Ba??ar??yla Ger??ekle??ti!");
                        refreshPersonel();

                    }
                }

                preparedStatement.close();
                connect.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            for (TextField t:textFields){
                t.clear();
            }


        }


    }

    public void refreshPersonel(){
        TextField[] textFields={personelKullaniciAdTf,sifreTf};
        idLabel.setText("");
        idTextLabel.setVisible(false);


        for (TextField t:textFields){
            t.clear();
        }
        choiceBox.setValue("");

        personelRefreshButton.arm();
        personelRefreshButton.disarm();
        personellerTable.getItems().clear();


        personelIdColoumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        personelAdColoumn.setCellValueFactory(new PropertyValueFactory<>("personelAd"));
        sifreColoumn.setCellValueFactory(new PropertyValueFactory<>("sifre"));
        calisanTipiColoumn.setCellValueFactory(new PropertyValueFactory<>("calisanTipi"));

        selectData dataPersonel=new selectData();
        personellerTable.setItems(dataPersonel.selectPersonelData());

    }
    public void refreshPersonelAll(){
        refreshPersonel();
        uyariLabel1.setText("");
    }

    public void updatePersonel(){
        TextField[] textFields={personelKullaniciAdTf,sifreTf};
        if (personelKullaniciAdTf.getText().isEmpty() || sifreTf.getText().isEmpty()){
            for (TextField t:textFields){
                if (t.getText().isEmpty())
                    t.setPromptText("l??tfen de??er giriniz");
            }
            uyariLabel1.setTextFill(Color.RED);
            uyariLabel1.setText("L??tfen bo?? yer b??rakmay??n!");
        }else {

            editData editData=new editData("kullanicilar.kullanicibilgileri","Kullanici_adi","Sifre","Calisan_Tipi","id",personelKullaniciAdTf,sifreTf,choiceBox,idLabel.getText());
            uyariLabel1.setTextFill(Color.valueOf("#019710"));
            uyariLabel1.setText("??al????an D??zenlendi!");

            for (TextField t:textFields){
                t.clear();
            }

            refreshPersonel();

        }

    }

    public void deletePersonel(){
        TextField[] textFields={personelKullaniciAdTf,sifreTf};
        if (personelKullaniciAdTf.getText().isEmpty() || sifreTf.getText().isEmpty() || choiceBox.getSelectionModel().isEmpty()){
            for (TextField t:textFields){
                if (t.getText().isEmpty())
                    t.setPromptText("l??tfen de??er giriniz");
            }
            uyariLabel1.setTextFill(Color.RED);
            uyariLabel1.setText("L??tfen bo?? yer b??rakmay??n!");
        }else {


            deleteData deleteData=new deleteData("kullanicilar.kullanicibilgileri","id",idLabel.getText());
            uyariLabel1.setTextFill(Color.valueOf("#019710"));
            uyariLabel1.setText("??al????an Silindi!");


            for (TextField t:textFields){
                t.clear();
            }

            refreshPersonel();


        }

    }

    public void personelTableSelected(){
        try {
            uyariLabel1.setText("");
            Personel selected=personellerTable.getSelectionModel().getSelectedItem();
            idTextLabel.setVisible(true);


            idLabel.setText(String.valueOf(selected.getId()));
            personelKullaniciAdTf.setText(selected.getPersonelAd());
            sifreTf.setText(String.valueOf(selected.getSifre()));
            choiceBox.setValue(selected.getCalisanTipi());
        }catch (NullPointerException e){
            System.out.println("tablodan ??al????an se??ilmedi");
        }


    }

    public void satis(){
        TextField[] textFields={urunAdTf1,urunAdetTf1,alisFiyatiTf1,satisFiyatiTf1};



         if (urunlerTable1.getSelectionModel().isEmpty()){
             uyariLabel2.setTextFill(Color.RED);
             uyariLabel2.setText("L??tfen tablodan ??r??n se??iniz!");
        }
         else if (sat??lanAdetTf.getText().isEmpty() || urunAdetTf1.getText().isEmpty() || urunAdTf1.getText().isEmpty() || alisFiyatiTf1.getText().isEmpty() || satisFiyatiTf1.getText().isEmpty()) {

                for (TextField t : textFields) {
                    t.setPromptText("tablodan ??r??n se??iniz");
                }
                sat??lanAdetTf.setPromptText("l??tfen sat??n alaca????n??z miktar?? giriniz");

                uyariLabel2.setTextFill(Color.RED);
                uyariLabel2.setText("L??tfen sat??n alaca????n??z miktar?? giriniz!");
            }
            else {

                if (Integer.parseInt(urunAdetTf1.getText())<Integer.parseInt(sat??lanAdetTf.getText())){
                    uyariLabel2.setTextFill(Color.RED);
                    uyariLabel2.setText("Stoktakta girilen adette ??r??n yok!");
                }else {

                    editData editData = new editData("urunler.mevcut_urunler", "urun_adedi", "urun_numarasi", urunAdetTf1, sat??lanAdetTf, urunNumaras??Label1.getText());
                    uyariLabel2.setTextFill(Color.valueOf("#019710"));
                    uyariLabel2.setText("Sat??ld??!");


                    for (TextField t : textFields) {
                        t.clear();
                    }

                    refreshSatis();

                }
            }


    }


    public void refreshSatis(){


        TextField[] textFields={alisFiyatiTf1,satisFiyatiTf1,urunAdTf1,urunAdetTf1,sat??lanAdetTf};
        try {
            for (TextField t:textFields){
                t.clear();}

                urunNumaras??Label1.setText("");
                ??r??nNoTextLabel1.setVisible(false);

                urunlerTable1.getItems().clear();

                urunNumarasiKolonu1.setCellValueFactory(new PropertyValueFactory<>("numara"));
                isimKolonu1.setCellValueFactory(new PropertyValueFactory<>("urunIsim"));
                adetKolonu1.setCellValueFactory(new PropertyValueFactory<>("urunAdet"));
                alisFiyatiKolonu1.setCellValueFactory(new PropertyValueFactory<>("urunAlis"));
                satisFiyatikolonu1.setCellValueFactory(new PropertyValueFactory<>("urunSatis"));
                karKolonu1.setCellValueFactory(new PropertyValueFactory<>("urunKar"));
                selectData data=new selectData();
                urunlerTable1.setItems(data.selectStokData());

        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }



    }
    public void refreshSatisAll(){
        refreshSatis();
        uyariLabel2.setText("");
    }

    public void tableSelected1(){
        try {
            uyariLabel2.setText("");
            urun selected1=urunlerTable1.getSelectionModel().getSelectedItem();
            ??r??nNoTextLabel1.setVisible(true);


            urunNumaras??Label1.setText(String.valueOf(selected1.getNumara()));
            urunAdTf1.setText(selected1.getUrunIsim());
            urunAdetTf1.setText(String.valueOf(selected1.getUrunAdet()));
            alisFiyatiTf1.setText(String.valueOf(selected1.getUrunAlis()));
            satisFiyatiTf1.setText(String.valueOf(selected1.getUrunSatis()));
        }catch (NullPointerException e){
            System.out.println("sat???? i??in ??r??n se??ilmedi!");
        }

    }



    public void mouseBas??ld??(){
        Button[] butonliste={ekleButton,refreshButton,deleteButton,updateButton,personelEkleButton,personelRefreshButton,deletePersonelButton,updatePersonelButton,anamenuButon,ekleButton1,refreshButton1};

        for (Button i:butonliste){
            if (i.isPressed())
                i.setUnderline(true);
        }

    }

    public void mouseKald??r??ld??(){
        Button[] butonliste={ekleButton,refreshButton,deleteButton,updateButton,personelEkleButton,personelRefreshButton,deletePersonelButton,updatePersonelButton,anamenuButon,ekleButton1,refreshButton1};

        for (Button i: butonliste){
            i.setUnderline(false);
        }

    }

    public void anamenudon(ActionEvent event) throws IOException {
         Stage stage;
         Scene scene;


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("giris-view.fxml"));
            Parent root=fxmlLoader.load();
            stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
            Image icon=new Image("C:\\Users\\User\\IdeaProjects\\javaFxStokTakipProgrami\\src\\main\\resources\\com\\example\\javafxstoktakipprogrami\\box-with-up-arrow-on-a-trolley.png");
            stage.getIcons().add(icon);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }






}
