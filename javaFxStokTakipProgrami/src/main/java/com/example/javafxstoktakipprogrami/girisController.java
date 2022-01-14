package com.example.javafxstoktakipprogrami;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class girisController implements Initializable {


    public Button kayıtButton;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public CheckBox sifreGosterCb;
    public Label sifreLabel,uyariLabel;
    @FXML
    private PasswordField sifrePf;
    @FXML
    private TextField adTf;
    @FXML
    private Text kayitsizText;
    @FXML
    private Button girisButton;
    @FXML
    private ToggleGroup calisanlar;
    @FXML
    private RadioButton yoneticiRb,personelRb;

    String calisanTipi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        adTf.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    girisButton.fire();
                }
            }
        });

        sifrePf.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    girisButton.fire();
                }
            }
        });



    }



    @FXML
    protected void giris(ActionEvent event) throws IOException, SQLException {
        if (adTf.getText().isEmpty() && sifrePf.getText().isEmpty()){
            uyariLabel.setText("Lütfen kullanıcı adı ve sifre giriniz.");
        }
        if (yoneticiRb.isSelected())
            calisanTipi=yoneticiRb.getText();
        else if (personelRb.isSelected())
            calisanTipi=personelRb.getText();
        else
            calisanTipi="";

        Connection connect;
        DbHelper dbHelper=new DbHelper();
        PreparedStatement preparedStatement ;

        String sql="SELECT count(1) FROM kullanicilar.kullanicibilgileri WHERE Kullanici_Adi='"+adTf.getText()+"' AND Sifre='"+sifrePf.getText()+"' AND Calisan_Tipi='"+calisanTipi+"'" ;


        try {
            connect=dbHelper.getConnection();
            preparedStatement=connect.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery(sql);
            while (resultSet.next()){
                if (resultSet.getInt(1)==1){
                    if (yoneticiRb.isSelected()){
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("yonetici-stok-view.fxml"));
                        Parent root=fxmlLoader.load();
                        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
                        scene=new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        Image icon=new Image("C:\\Users\\User\\IdeaProjects\\javaFxStokTakipProgrami\\src\\main\\resources\\com\\example\\javafxstoktakipprogrami\\box-with-up-arrow-on-a-trolley.png");
                        stage.getIcons().add(icon);
                    }else if (personelRb.isSelected()){
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("personel-stok-view.fxml"));
                        Parent root=fxmlLoader.load();
                        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
                        scene=new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        Image icon=new Image("C:\\Users\\User\\IdeaProjects\\javaFxStokTakipProgrami\\src\\main\\resources\\com\\example\\javafxstoktakipprogrami\\box-with-up-arrow-on-a-trolley.png");
                        stage.getIcons().add(icon);

                    }else
                        System.out.println(calisanTipi);
                        System.out.println("ekran gelmedi");


                }else {
                    uyariLabel.setText("Kullanıcı Adı, Şifre veya Çalışan Tipi Hatalı!");
                }
            }
            preparedStatement.close();
            connect.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    @FXML
    protected void kayit(ActionEvent event) throws InterruptedException {



        System.out.println("this clicked");


        yeniEkranController.main("Kayıt ol","kayıt-view.fxml");

    }

    @FXML
    protected void sifreGoster(){
        if (sifreGosterCb.isSelected()) {
            sifreLabel.setText(sifrePf.getText());
        }else
            sifreLabel.setText("");

    }

    public void mouseBasıldı(){
        if (kayıtButton.isPressed())
            kayıtButton.setUnderline(true);
        else if(girisButton.isPressed())
            girisButton.setUnderline(true);
    }
    public void mouseKaldırıldı(){
        kayıtButton.setUnderline(false);
        girisButton.setUnderline(false);
    }







}