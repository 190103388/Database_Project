package sample;
import java.sql.*;


public class DatabaseConnection {
    public Connection databaseLink;


    public  Connection getConnection(){
        String dbURL = "jdbc:oracle:thin:@localhost:1521/xe";
        String username = "hr";
        String password = "hr";
        try {
            databaseLink = DriverManager.getConnection(dbURL,username,password);
            System.out.println("Connected to Oracle database server");
        } catch (SQLException e) {
            System.out.println("Oops, error!");
            e.printStackTrace();
        }
        return databaseLink;
    }
    public ResultSet getSet(String SQL) throws SQLException {
        Connection connection = getConnection();
        Statement stmt=connection.createStatement();
        ResultSet resultSet;
        resultSet = stmt.executeQuery(SQL);
        return resultSet;
    }


}