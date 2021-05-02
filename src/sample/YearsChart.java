package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class YearsChart {
    private SimpleIntegerProperty count;
    private SimpleStringProperty year;
    public YearsChart() {

    }
    public YearsChart(int count, String year) {
        this.count = new SimpleIntegerProperty(count);
        this.year = new SimpleStringProperty(year);
    }

    public int getCount() {
        return count.get();
    }

    public SimpleIntegerProperty countProperty() {
        return count;
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public String getYear() {
        return year.get();
    }

    public SimpleStringProperty yearProperty() {
        return year;
    }

    public void setYear(String year) {
        this.year.set(year);
    }

    ObservableList<PieChart.Data>observableList;

    public ObservableList<PieChart.Data> getChart(){
        observableList = FXCollections.observableArrayList();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String SQL = "SELECT COUNT(*),year FROM sorted_movies GROUP BY year order by year";

        ResultSet resultSet = null;
        try {
            resultSet = databaseConnection.getSet(SQL);
            if (resultSet.next()){
                while (resultSet.next()){
                    PieChart.Data yearsChart = new PieChart.Data(resultSet.getString(2),Integer.parseInt(resultSet.getString(1)));
                    observableList.add(yearsChart);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return observableList;

    }
}
