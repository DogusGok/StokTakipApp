package com.example.javafxstoktakipprogrami;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class kayıtController {

    public Button vazgecButton;
    @FXML
    private RadioButton yoneticiRb,personalRb;
    @FXML
    private ToggleGroup kayıtCalisanTipi;
    @FXML
    private TextField kullaniciAdiTf,sifreTf;
    @FXML
    private Button kaydetButton;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label kayıtLabel;


    public void sorgulaKaydet(){
        String sql="SELECT count(1) FROM kullanicilar.kullanicibilgileri WHERE Kullanici_Adi='"+kullaniciAdiTf.getText()+"'" ;
        try {
            Connection connect = DbHelper.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery(sql);
            while (resultSet.next()){
                if (resultSet.getInt(1)==1){

                    kayıtLabel.setText("Bu Kullanıcı Adı Alınmış!");
                    kayıtLabel.setTextFill(Color.RED);
                }else {
                    kayıtLabel.setTextFill(Color.valueOf("#019710"));
                    kaydet();
                }
            }
            preparedStatement.close();
            connect.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void kaydet()  {
        insertData insertData=new insertData("kullanicilar.kullanicibilgileri","Kullanici_adi","Sifre",kullaniciAdiTf,sifreTf);

        kayıtLabel.setText("( Kaydınız Başarıyla Gerçekleşti ), Bu sayfayı kapatabilirsiniz!");

    }

    public void vazgec(){
        Stage stage = (Stage) vazgecButton.getScene().getWindow();
        stage.close();
    }



}
