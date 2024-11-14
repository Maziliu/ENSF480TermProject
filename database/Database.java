package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private static Database INSTANCE;
    private static Connection CONNECTION;
    private static final String URL = "jdbc:mysql://localhost:3306/MovieReservationDB";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    private Database() {
        try{
            CONNECTION = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e){
            System.err.println("ERROR CONNECTION FAILED: " + e.getMessage());
        }
    }

    public static Database getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Database();
        }

        return INSTANCE;
    }

    public ResultSet executePreparedStatement(String query, String... queryVariables){
        ResultSet resultSet = null;
        try{
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
            int index = 1;
            for(String queryVariable : queryVariables){
                preparedStatement.setString(index, queryVariable);
                index++;
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e){
            System.err.println("ERROR EXECUTING PREPARED STATEMENT: " + e.getMessage());
        }

        return resultSet;
    }
}