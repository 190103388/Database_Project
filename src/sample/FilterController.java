package sample;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class FilterController implements Initializable {
    @FXML
    private TableView<Films> table;

    @FXML
    private ComboBox<String> GENRE;

    @FXML
    private ComboBox<String> AGE;

    @FXML
    private ComboBox<String> YEAR;

    @FXML
    private TableColumn<Films, ImageView> IMG_COL;

    @FXML
    private TableColumn<Films, String> Title;

    @FXML
    private TableColumn<Films, String> GENRE_COL;

    @FXML
    private TableColumn<Films, String> AGE_COL;

    @FXML
    private TableColumn<Films, String> YEAR_COL;

    @FXML
    private Button SEARCH;



    ObservableList<Films> listM;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        AGE.getItems().addAll( "","G", "PG", "PG-13", "R", "NC-17");

        GENRE.getItems().addAll( "","Action",  "Adventure", "Sci-Fi", "Thriller", "Comedy", "Western", "Family", "Animation", "Biography", "Drama", "Music", "War", "Crime", "Fantasy", "History", "Mystery", "Romance", "Horror", "Documentary","News","Sport");
        YEAR.getItems().addAll( "","all","1960-1970", "1970-1980", "1980-1990", "1990-2000", "2000-2010", "2010-2020");

    }

    public void search(){
        Films films = new Films();

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String SQL = "SELECT images,title,year,age_rating,genres FROM sorted_movies ";
        String age = null;
        String genre =  null;
        String year =  null;

        try{
            genre = GENRE.getSelectionModel().getSelectedItem().toString();
        }catch (Exception ex){

            genre="";
        }
        try{
            age = AGE.getSelectionModel().getSelectedItem().toString();
        }catch (Exception ex){

            age = "";
        }
        try{
            year = YEAR.getSelectionModel().getSelectedItem().toString();
        }catch (Exception ex){
            year ="";
        }




        boolean agePassed = false;
        if (age.length()!=0){

            SQL+="WHERE age_rating = '"+age+"' ";
            agePassed = true;
        }

        boolean genrePassed = false;
        if (genre.length()!=0){
            if (agePassed){
                SQL+="AND genres LIKE '%"+genre+"%'";
            }else{
                SQL+="WHERE genres LIKE '%"+genre+"%'";
            }
            genrePassed = true;
        }
        if (year.length()!=0){
            if (genrePassed || agePassed ){
                String[]arr =year.split("-");
                int num1 = Integer.parseInt(arr[0]);
                int num2 = Integer.parseInt(arr[1]);
                String newsql = "";
                for (int i = num1; i <num2 ; i++) {
                    if (i !=num2-1){
                        newsql += "'"+ i+"',";
                    }else {
                        newsql += "'"+ i+"' ";
                    }
                }
                SQL+=" AND year IN ("+newsql+") ";
            }else {
                String[]arr =year.split("-");
                int num1 = Integer.parseInt(arr[0]);
                int num2 = Integer.parseInt(arr[1]);
                String newsql = "";
                for (int i = num1; i <num2 ; i++) {
                    if (i !=num2-1){
                        newsql += "'"+ i+"',";
                    }else {
                        newsql += "'"+ i+"' ";
                    }
                }
                SQL+=" WHERE year IN ("+newsql+") ";
            }
        }
        listM =films.getObservableList(SQL);
        System.out.println(SQL);
        table.setItems(listM);
//        ImageView imageView = new ImageView();
        IMG_COL.setCellValueFactory(new PropertyValueFactory<>("Images"));
        Title.setCellValueFactory(new PropertyValueFactory<Films, String>("Title"));
        AGE_COL.setCellValueFactory(new PropertyValueFactory<Films, String>("Age_rating"));
        GENRE_COL.setCellValueFactory(new PropertyValueFactory<Films, String>("Genres"));
        YEAR_COL.setCellValueFactory(new PropertyValueFactory<Films, String>("Years"));



//        IMG_COL.setCellValueFactory(new  PropertyValueFactory<Films,ImageView> >("img"));
//        Title.setCellValueFactory(new PropertyValueFactory<Films,String> ("title"));
//        YEAR_COL.setCellValueFactory(new PropertyValueFactory<Films,String> ("year"));
//        AGE_COL.setCellValueFactory(new PropertyValueFactory<Films,String> ("age_rating"));
//        GENRE_COL.setCellValueFactory(new PropertyValueFactory<Films,String> ("genres"));


    }

}

