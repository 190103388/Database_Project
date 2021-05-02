package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.chart.XYChart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ages {
    private SimpleIntegerProperty count;
    private SimpleStringProperty ages;
    public Ages() {
    }
    public Ages(int count, String ages) {
        this.count = new  SimpleIntegerProperty(count);
        this.ages = new SimpleStringProperty(ages);
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

    public String getAges() {
        return ages.get();
    }

    public SimpleStringProperty agesProperty() {
        return ages;
    }

    public void setAges(String ages) {
        this.ages.set(ages);
    }

    public XYChart.Series getXY() throws SQLException {
        XYChart.Series set1 = new XYChart.Series<>();

        String SQL = "SELECT COUNT(*),age FROM sorted_movies GROUP BY age order by age";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ResultSet resultSet =databaseConnection.getSet(SQL);

        while (resultSet.next()){
            set1.getData().add(new XYChart.Data(resultSet.getString(2), Integer.parseInt(resultSet.getString(1))));
        }
        return set1;
    }
}
