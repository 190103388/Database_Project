package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Films {

    private SimpleObjectProperty images;
    private SimpleStringProperty title;
    private SimpleStringProperty age_rating;
    private SimpleStringProperty years;
    private SimpleStringProperty genres;

    public Films(){

    }
    public Films(String images,String title, String age_rating, String year, String genres) {
        ImageView imageView = new ImageView(new Image(images));
        imageView.setFitWidth(90);
        imageView.setFitHeight(160);
        this.images = new SimpleObjectProperty<ImageView>(imageView);
        this.title =  new SimpleStringProperty(title);
        this.age_rating = new SimpleStringProperty(age_rating);
        this.years = new SimpleStringProperty(year);
        this.genres = new SimpleStringProperty(genres);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public String getAge_rating() {
        return age_rating.get();
    }

    public SimpleStringProperty age_ratingProperty() {
        return age_rating;
    }

    public String getYears() {
        return years.get();
    }

    public Object getImages() {
        return images.get();
    }

    public SimpleObjectProperty imagesProperty() {
        return images;
    }

    public void setImages(Object images) {
        this.images.set(images);
    }

    public SimpleStringProperty yearsProperty() {
        return years;
    }

    public String getGenres() {
        return genres.get();
    }

    public SimpleStringProperty genresProperty() {
        return genres;
    }


    ObservableList<Films>observableList;


    public  ObservableList<Films> getObservableList(String query) {
        observableList = FXCollections.observableArrayList();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ResultSet resultSet = null;
        try {
            resultSet = databaseConnection.getSet(query);
            if (resultSet.next()){
                while (resultSet.next()){
                    Films films = new Films(resultSet.getString("Images"),resultSet.getString("Title"),resultSet.getString("Age_rating"),resultSet.getString("Year") ,resultSet.getString("Genres"));
                    System.out.println(resultSet.getString("Title")+"  " +resultSet.getString("Age_rating")+" "+resultSet.getString("Year")+" "+resultSet.getString("Genres"));
                    observableList.add(films);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return observableList;
    }



}
