module com.example.javafxstoktakipprogrami {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    opens  com.example.javafxstoktakipprogrami to javafx.fxml;



    exports com.example.javafxstoktakipprogrami;
}


