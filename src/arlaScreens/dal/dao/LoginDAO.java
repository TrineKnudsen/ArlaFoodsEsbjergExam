package arlaScreens.dal.dao;

import arlaScreens.dal.JDBCConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {

    private final JDBCConnectionPool connectionPool;

    public LoginDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    public boolean checkLogin(String username, String password) {

        try(Connection connection = connectionPool.checkOut()) {
            String sql = "SELECT username, password FROM AdminLogin where username='"+username+"' AND password='"+password+"'";
            Statement statement = connection.createStatement();

            if(statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                if(resultSet.next()){
                    System.out.println("Success");
                    return true;
                } else {
                    System.out.println("Failed");
                    return false;
                }
            } return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

}
