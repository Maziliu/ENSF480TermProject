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

    public ResultSet executeSelectPreparedStatement(String query, String... queryVariables) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
        int index = 1;
        for(String queryVariable : queryVariables){
            preparedStatement.setString(index, queryVariable);
            index++;
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public void executeDatabaseAlteringPreparedStatement(String query, String... queryVariables) throws SQLException {
        PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
        int index = 1;
        for(String queryVariable : queryVariables){
            preparedStatement.setString(index, queryVariable);
            index++;
        }
        preparedStatement.executeUpdate();
    }
}