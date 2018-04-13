package application;

import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;
import java.sql.Statement ;
import java.sql.ResultSet ;

import java.util.List ;
import java.util.ArrayList ;

public class UserDataAccessor {

    private Connection connection ;

    public UserDataAccessor(String driverClassName, String dbURL) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public List<User> getUserList() throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("select * from person");
        ){
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                User user = new User(username, password);
                userList.add(user);
            }
            return userList ;
        } 
    }

	
}
