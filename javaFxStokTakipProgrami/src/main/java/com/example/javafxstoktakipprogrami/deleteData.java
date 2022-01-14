package com.example.javafxstoktakipprogrami;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteData {

    Connection connect;
    DbHelper dbHelper=new DbHelper();
    PreparedStatement preparedStatement ;



    public  deleteData(String dataName,String degisken1,String urunNo){
        try {
            connect = dbHelper.getConnection();
            String sql = "delete from " + dataName +" where "+degisken1+"='"+urunNo+"' " ;

            preparedStatement=connect.prepareStatement(sql);
            preparedStatement.execute();
            preparedStatement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
