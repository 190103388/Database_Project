package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SearchController  implements Initializable {
    @FXML
    private ImageView movieView;

    @FXML
    private Label movieName;

    @FXML
    private Label movieAge;

    @FXML
    private Label genresInfo;

    String SgenresInfo;

    @FXML
    private Label yearInfo1;

    @FXML
    private Label directorInfo1;

    @FXML
    private Label countryInfo1;

    @FXML
    private Label languageInfo1;

    @FXML
    private Label runtimeInfo1;

    @FXML
    private Label TomatoesInfo12;

    @FXML
    private Label IMDBInfo11;

    @FXML
    private Label RTInfo111;

    @FXML
    private Label AverageInfo1111;

    @FXML
    private Button LikeButtom;

    @FXML
    private Button dissButtom;

    public void setTomatoesInfo12(String tomatoesInfo12) {
        TomatoesInfo12.setText(tomatoesInfo12);
    }

    public void setIMDBInfo11(String IMDBInfo11) {
        this.IMDBInfo11.setText(IMDBInfo11);
    }

    public void setRTInfo111(String RTInfo111) {
        this.RTInfo111.setText(RTInfo111);
    }

    public void setAverageInfo1111(String averageInfo1111) {
        AverageInfo1111.setText(averageInfo1111);
    }



    private int ID ;

    public void setMovieView(String link) {
        this.movieView.setImage(new Image(link));
    }

    public void setMovieName(String movieName) {
//        System.out.println(movieName);
        this.movieName.setText(movieName);
    }

    public void setMovieAge(String movieAge) {
        this.movieAge.setText(movieAge);
    }

    public void setYearInfo1(String yearInfo1) {
        this.yearInfo1.setText(yearInfo1);
    }

    public void setDirectorInfo1(String directorInfo1) {
        this.directorInfo1.setText(directorInfo1);
    }

    public void setCountryInfo1(String countryInfo1) {
        this.countryInfo1.setText(countryInfo1);
    }

    public void setLanguageInfo1(String languageInfo1) {
        this.languageInfo1.setText(languageInfo1);
    }

    public void setRuntimeInfo1(String runtimeInfo1) {
        this.runtimeInfo1.setText(runtimeInfo1);
    }

    //
//    public void setGenres(String genres) {
//        this.genres.setText(genres);
//    }
//
    public void setGenresInfo(String genres) {
        this.SgenresInfo = genres;
        this.genresInfo.setText(genres);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        String SQL = "SELECT * FROM sorted_movies Where ID =  "+this.ID;
//        ResultSet resultSet;
//        try {
//            DatabaseConnection databaseConnection = new DatabaseConnection();
//            Connection connection = databaseConnection.getConnection();
//            Statement stmt=connection.createStatement();
//            resultSet = stmt.executeQuery(SQL);
//            while (resultSet.next()){
//                movieName.setText(resultSet.getString(2));
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Oops, error!");
//            e.printStackTrace();
//        }
    }

    public Label getGenresInfo() {
        return genresInfo;
    }

    public void liked() throws SQLException {
        String SQL = "INSERT INTO user_preferences values (user_seq.nextval, 2 ,'"+SgenresInfo+"') ";
        System.out.println(SQL);
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.getSet(SQL);
        Stage stage = (Stage)LikeButtom.getScene().getWindow();
        stage.close();
    }

    public void disslike(){
        Stage stage = (Stage)dissButtom.getScene().getWindow();
        stage.close();
    }


}