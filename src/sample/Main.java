package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene =new Scene(root, 600, 400);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    


//    public static void main(String[] args) {
//        DatabaseConnection databaseConnection = new DatabaseConnection();
//        String SQL = "SELECT COUNT(*),year FROM sorted_movies GROUP BY year order by year";
//
//        ResultSet resultSet = null;
//        try {
//            resultSet = databaseConnection.getSet(SQL);
//            if (resultSet.next()){
//                while (resultSet.next()){
//                    System.out.println(resultSet.getString(1)+" "+resultSet.getString(2));
////                    observableList.add(yearsChart);
//                }
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//    }
}
