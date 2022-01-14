package com.example.javafxstoktakipprogrami;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectData {
    Connection connect;
    DbHelper dbHelper=new DbHelper();
    PreparedStatement preparedStatement ;


    public ObservableList<urun> selectStokData(){
         ObservableList<urun> data= FXCollections.observableArrayList();
        try {
            Connection con=DbHelper.getConnection();

            ResultSet rs=con.createStatement().executeQuery("SELECT * FROM urunler.mevcut_urunler");

            while (rs.next()){
                if (Integer.parseInt(rs.getString(3))==0)
                    continue;
                else
                data.add(new urun(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)) );

            }
        }catch (SQLException e){
            System.out.println(e.getErrorCode());
        }

         return data;
    }

    public ObservableList<Personel> selectPersonelData(){
        ObservableList<Personel> data= FXCollections.observableArrayList();
        try {
            Connection con=DbHelper.getConnection();

            ResultSet rs=con.createStatement().executeQuery("SELECT * FROM kullanicilar.kullanicibilgileri");

            while (rs.next()){
                data.add(new Personel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)) );

            }
        }catch (SQLException e){
            System.out.println(e.getErrorCode());
        }


        return data;
    }

}
