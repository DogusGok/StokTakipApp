package com.example.javafxstoktakipprogrami;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class yeniEkranController {

    public static void main(String title,String fxmlName) {
        FXMLLoader fxmlLoader=new FXMLLoader(yeniEkranController.class.getResource(fxmlName));
        try {

            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
            Image icon=new Image("C:\\Users\\User\\IdeaProjects\\javaFxStokTakipProgrami\\src\\main\\resources\\com\\example\\javafxstoktakipprogrami\\box-with-up-arrow-on-a-trolley.png");
            stage.getIcons().add(icon);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }


    }
}
