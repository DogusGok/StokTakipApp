package com.example.javafxstoktakipprogrami;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class insertData {
    Connection connect;
    DbHelper dbHelper=new DbHelper();
    PreparedStatement preparedStatement ;

    public insertData(String dataName,String degisken1,TextField textField1) throws SQLException {
        try {
            connect = dbHelper.getConnection();
            String sql = "insert into " + dataName + " (" + degisken1 + ")" + "values (?)";

            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, textField1.getText());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connect.close();
        }catch (SQLException exception){
            dbHelper.ShowError(exception);
        }


    }
    public insertData(String dataName,String degisken1,String degisken2,TextField textField1,TextField textField2){

        try {
            connect = dbHelper.getConnection();

            String sql = "insert into " + dataName + " (" + degisken1 +","+degisken2+",Calisan_Tipi)" + "values (?,?,?)";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, textField1.getText());
            preparedStatement.setString(2, textField2.getText());
            preparedStatement.setString(3,"Personel");

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connect.close();
        }catch (SQLException exception){
            dbHelper.ShowError(exception);
        }
    }

    public insertData(String dataName,String degisken1,String degisken2,String degisken3,TextField textField1,TextField textField2,TextField textField3){

        try {
            connect = dbHelper.getConnection();
            String sql = "insert into " + dataName + " (" + degisken1 +","+degisken2+","+degisken3+ ")" + "values (?,?,?)";

            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, textField1.getText());
            preparedStatement.setString(2, textField2.getText());
            preparedStatement.setString(3, textField3.getText());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connect.close();
        }catch (SQLException exception){
            dbHelper.ShowError(exception);
        }
    }
    public insertData(String dataName, String degisken1, String degisken2, String degisken3, TextField textField1, TextField textField2, ChoiceBox choiceBox){

        try {
            connect = dbHelper.getConnection();
            String sql = "insert into " + dataName + " (" + degisken1 +","+degisken2+","+degisken3+ ")" + "values (?,?,?)";
            //String sql = "insert into " + dataName + " (Kullanici_Adi,Sifre,Calisan_Tipi)" + "values (?,?,?)";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, textField1.getText());
            preparedStatement.setString(2, textField2.getText());
            preparedStatement.setString(3, (String) choiceBox.getSelectionModel().getSelectedItem());


            preparedStatement.executeUpdate();
            preparedStatement.close();
            connect.close();
        }catch (SQLException exception){
            dbHelper.ShowError(exception);
        }
    }

    public insertData(String dataName,String degisken1,String degisken2,String degisken3,String degisken4,String degisken5,TextField textField1,TextField textField2,TextField textField3,TextField textField4,String kar){

        try {
            connect = dbHelper.getConnection();
            String sql = "insert into " + dataName + " (" + degisken1 +","+degisken2+","+degisken3+","+degisken4+","+degisken5+ ")" + "values (?,?,?,?,?)";
            //String sql = "insert into " + dataName + " (Kullanici_Adi,Sifre,Calisan_Tipi)" + "values (?,?,?)";


            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, textField1.getText());
            preparedStatement.setString(2, textField2.getText());
            preparedStatement.setString(3, textField3.getText());
            preparedStatement.setString(4, textField4.getText());
            preparedStatement.setString(5, kar);


            preparedStatement.executeUpdate();
            preparedStatement.close();
            connect.close();
        }catch (SQLException exception){
            dbHelper.ShowError(exception);
        }
    }



}
