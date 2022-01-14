package com.example.javafxstoktakipprogrami;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class editData {
    Connection connect;
    DbHelper dbHelper=new DbHelper();
    PreparedStatement preparedStatement ;

    public editData(String dataName, String degisken1, String degisken2, String degisken3, String degisken4, String degisken5,String degisken6,
                    TextField textField1, TextField textField2, TextField textField3, TextField textField4, String kar,String urunNo){
        try {
            connect = dbHelper.getConnection();
            String sql = "update  " + dataName +" set "+degisken1+"='"+textField1.getText()+"',"+degisken2+"='"+textField2.getText()+"',"+degisken3+
                    "='"+textField3.getText()+"',"+degisken4+"='"+textField4.getText()+"',"+degisken5+"='"+kar+"' where "+degisken6+"='"+urunNo+"' " ;

            preparedStatement = connect.prepareStatement(sql);

            preparedStatement.execute();
            preparedStatement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public editData(String dataName, String kullanici_adi, String sifre, String calisan_tipi, String id, TextField personelKullaniciAdTf, TextField sifreTf, ChoiceBox choiceBox, String text) {

        try {
            connect = dbHelper.getConnection();
            String sql = "update  " + dataName +" set "+kullanici_adi+"='"+personelKullaniciAdTf.getText()+"',"+sifre+"='"+sifreTf.getText()+"',"+calisan_tipi+"='"+choiceBox.getSelectionModel().getSelectedItem()+"' where "+id+"='"+text+"' " ;

            preparedStatement = connect.prepareStatement(sql);

            preparedStatement.execute();
            preparedStatement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public editData(String dataName, String urun_adedi, String urun_numarasi, TextField urunAdetTf,TextField satilanAdet, String text) {

        if (Integer.parseInt(satilanAdet.getText())<=Integer.parseInt(urunAdetTf.getText())){

            try {
                connect = dbHelper.getConnection();
                String sql = "update  " + dataName +" set "+urun_adedi+"="+urunAdetTf.getText()+"-"+satilanAdet.getText()+"" + " where "+urun_numarasi+"='"+text+"' " ;

                preparedStatement = connect.prepareStatement(sql);

                preparedStatement.execute();
                preparedStatement.close();
                connect.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            System.out.println("fazla miktar girdiniz");
        }



    }
}
