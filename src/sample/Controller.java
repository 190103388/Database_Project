package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private ImageView imageV_1;

    @FXML
    private Label labelName1;

    @FXML
    private Button viewMore1;

    @FXML
    private ImageView imageV_2;

    @FXML
    private Label labelName2;

    @FXML
    private Button viewMore2;

    @FXML
    private ImageView imageV_3;

    @FXML
    private Label labelName3;

    @FXML
    private Button viewMore3;

    @FXML
    private TextArea searchedText;


    @FXML
    private ListView<String> searchList;


    @FXML
    private PieChart PIE_CHAR;


    @FXML
    private BarChart<?, ?> BARCHART;

    String[][]links;
    int[] randomSan;
    ResultSet resultSet;

    String[]years = new String[]{};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Ages ages = new Ages();
        XYChart.Series set1 = null;
        try {
            set1 = ages.getXY();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        BARCHART.getData().addAll(set1);

        YearsChart yearsChart = new YearsChart();

        ObservableList<PieChart.Data> pieChartData = yearsChart.getChart();

        PIE_CHAR.setData(pieChartData);


        DatabaseConnection databaseConnection = new DatabaseConnection();
        searchedText.setOnKeyTyped(e->{
            System.out.println(searchedText.getText());
            String SQL = "SELECT search_movies('"+searchedText.getText()+"') FROM dual";
            String n = "";
            try {
                resultSet = databaseConnection.getSet(SQL);
                while (resultSet.next()){
                    n = resultSet.getString(1);
                    System.out.println(resultSet.getString(1));
                }
                String[]arr = n.split(";");
                searchList.getItems().clear();
                for (int i = 0; i <arr.length ; i++) {
                    searchList.getItems().add(arr[i]);
                }



            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        searchList.setOnMouseClicked(cc->{
            String currentCurrency = searchList.getSelectionModel().getSelectedItem().toString();
            try {
                searcing(currentCurrency);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        ImageView[] imageViews = new ImageView[]{imageV_1,imageV_2,imageV_3};
        Label[]labels = new Label[]{labelName1,labelName2,labelName3};
        randomSan =new int[3];
        String link = null;
        String SQL = "SELECT * FROM sorted_movies Where  ";
        for (int i = 0; i <randomSan.length ; i++) {
            randomSan[i] =(int) (Math.random()*500)+1;
            if (i!=0){
                SQL+=" OR ID = "+ randomSan[i];
            }else{
                SQL+="ID = "+ randomSan[i];
            }
            System.out.println(randomSan[i]);
        }
        try{
            ResultSet resultSet = databaseConnection.getSet(SQL);
//            this.resultSet = stmt.executeQuery(SQL);
            int i =0;
            links = new String[3][15];
            while (resultSet.next()){
                for (int j = 1; j <=14; j++) {
                    links[i][j] = resultSet.getString(j);
                }
                imageViews[i].setImage(new Image(links[i][12]));
                labels[i].setText(links[i][2]);
//                System.out.println(link);
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Oops, error!");
            e.printStackTrace();
        }





    }
    public void viewMore1() throws IOException {
        viewMore(0);
    }
    public void viewMore2() throws IOException {
        viewMore(1);
    }
    public void viewMore3() throws IOException {
        viewMore(2);
    }
    public void viewMore(int a) throws IOException {
        Stage primaryStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("film_info.fxml"));
        Parent root = fxmlLoader.load();

        SearchController searchController = fxmlLoader.getController();
        int i =0;
        searchController.setMovieName(links[a][2]);
        searchController.setMovieAge(links[a][4]);
        searchController.setMovieView(links[a][12]);
        searchController.setGenresInfo(links[a][8]);
        searchController.setDirectorInfo1(links[a][7]);
        searchController.setLanguageInfo1(links[a][10]);
        searchController.setYearInfo1(links[a][3]);
        searchController.setCountryInfo1(links[a][9]);
        searchController.setTomatoesInfo12(links[0][11]);
        searchController.setIMDBInfo11(links[0][5]);
        searchController.setAverageInfo1111(links[0][14]);
        searchController.setRTInfo111(links[0][6]);
        System.out.println("123");
//            i++;



        primaryStage.setTitle("Movie Info");

        primaryStage.setScene(new Scene(root, 1280, 750));
        primaryStage.show();
//        SearchController searchController = new SearchController(randomSan[0]);
    }
    private int a =0;
    public void searcing() throws IOException, SQLException {
        String searching = searchedText.getText();
        searcing(searching);
    }
    public void searcing(String s) throws IOException, SQLException {
        int a =2;
        String searching = searchedText.getText();
//        String SQL = "SELECT * FROM sorted_movies Where title LIKE  '%"+s+"%'";
        String SQL2="select get_movie('"+s+"') from dual";
        System.out.println(SQL2);

        String[][] info = new String[1][15];
//        ResultSet resultSet = null;
//        try {
//            DatabaseConnection databaseConnection = new DatabaseConnection();
//            resultSet = databaseConnection.getSet(SQL2);
//        } catch (SQLException e) {
//            System.out.println("Oops, error!");
//            e.printStackTrace();
//        }
//
//            while (resultSet.next()){
//                for (int i = 1; i <=14 ; i++) {
//                    info[0][i] = resultSet.getString(i);
//                }
//
//            }
        String n = "";
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            System.out.println("Connected to Oracle database server");
            Statement stmt=connection.createStatement();
            ResultSet resultSet =stmt.executeQuery(SQL2);
            while (resultSet.next()){
                n = resultSet.getString(1);
                System.out.println(n);
            }
            String[]arr = n.split(";");
            for (int i = 1; i <=14 ; i++) {
                info[0][i] = arr[i-1];
                System.out.println(info[0][i]);
            }
            if (this.a==a){
                this.a++;
            }
            a++;

        } catch (SQLException e) {
            System.out.println("Oops, error!");
            e.printStackTrace();
        }

        if (info[0][1]==null){
            return;
        }

        Stage primaryStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("film_info.fxml"));
        Parent root = fxmlLoader.load();

        SearchController searchController = fxmlLoader.getController();

        searchController.setMovieName(info[0][2]);
        searchController.setMovieAge(info[0][4]);
        searchController.setMovieView(info[0][12]);
        searchController.setGenresInfo(info[0][8]);
        searchController.setDirectorInfo1(info[0][7]);
        searchController.setLanguageInfo1(info[0][10]);
        searchController.setYearInfo1(info[0][3]);
        searchController.setCountryInfo1(info[0][9]);


        searchController.setTomatoesInfo12(info[0][11]);
        searchController.setIMDBInfo11(info[0][5]);
        searchController.setAverageInfo1111(info[0][14]);
        searchController.setRTInfo111(info[0][6]);
        System.out.println("123");

        System.out.println(info[0][11]+" "+info[0][5]+" "+info[0][14]+" "+info[0][6]+" ");
        primaryStage.setTitle("Movie Into");

        primaryStage.setScene(new Scene(root, 1280, 750));
        primaryStage.show();


    }

   public void filter() throws IOException {
       Stage primaryStage = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("filter.fxml"));
//       Stage primaryStage.initStyle(StageStyle.UNDECORATED);
       Scene scene =new Scene(root, 1280, 800);
       scene.getStylesheets().add("style.css");
       primaryStage.setScene(scene);
       primaryStage.show();
   }





}
