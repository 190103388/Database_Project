package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.xml.transform.Result;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField Usename_feald;

    @FXML
    private PasswordField Pasword_feald;

    @FXML
    private Button login_btn;

    @FXML
    private Button cancel_btn;

    @FXML
    private Label loginMessage;

    @FXML
    private ImageView logImageView;

    @FXML
    private ImageView zamokImageView;


    public void cancelBtnonAction( ){
        Stage stage = (Stage)cancel_btn.getScene().getWindow();
        stage.close();
    }

    public void loginBtnonActio(){
        if (Usename_feald.getText().isBlank() == false &&Pasword_feald.getText().isBlank()== false){
            validateLogin();
        }else{
            loginMessage.setText("Please enter username and password");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void validateLogin(){
        DatabaseConnection connection = new DatabaseConnection();
        String SQL = "SELECT * FROM user_account WHERE USERNAME='"+Usename_feald.getText()+"' and USER_PASSWORD = '"+Pasword_feald.getText()+"'";
        System.out.println(SQL);
        ResultSet resultSet;
        try {
            resultSet =connection.getSet(SQL);
//            System.out.println(resultSet.next());
            while (resultSet.next()){
                System.out.println(resultSet.getString(1));
                if (resultSet.getInt(1) ==2){
                    loginMessage.setText("Congurantsulations");
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    Scene scene =new Scene(root, 1280, 800);
                    scene.getStylesheets().add("style.css");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    Stage stage = (Stage)login_btn.getScene().getWindow();
                    stage.close();
                }else {
                    loginMessage.setText("invalid Login . Please try again");
                }
            }
        }catch (Exception e){
//            e.printStackTrace();
//            e.getCause();
            System.out.println(e);
        }
    }
}
