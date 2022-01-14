package com.example.javafxstoktakipprogrami;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("giris-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Stok Takip Programı");
        stage.setScene(scene);
        stage.show();
        Image icon=new Image("C:\\Users\\User\\IdeaProjects\\javaFxStokTakipProgrami\\src\\main\\resources\\com\\example\\javafxstoktakipprogrami\\box-with-up-arrow-on-a-trolley.png");
        stage.getIcons().add(icon);

    }

    public static void main(String[] args) {
        launch();
    }

}