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




public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene =new Scene(root, 1280, 800);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    


//    public static void main(String[] args) {
//        String SQL = "SELECT * FROM user_acc WHERE username = 'ravil' AND user_password = '12345'";
//        String n = null;
//        try {
//            DatabaseConnection databaseConnection = new DatabaseConnection();
//            ResultSet resultSet =databaseConnection.getSet(SQL);
//            System.out.println(resultSet.next());
//            while (resultSet.next()){
//
//                n = resultSet.getString(2)+" "+resultSet.getString(3);
//                System.out.println(resultSet.getInt(1));
//            }
//        } catch (SQLException e) {
//            System.out.println("Oops, error!");
//            e.printStackTrace();
//        }

//    }
}
