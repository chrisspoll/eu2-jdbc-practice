package jdbctests;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        String dbUrl ="jdbc:oracle:thin:@100.25.34.235:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        //create the connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement();
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select first_name,last_name,salary from employees");

        //move pointer to first row
 //       resultSet.next();

//        System.out.println(resultSet.getString("region_name"));

        while (resultSet.next()){
            System.out.println(resultSet.getString(1) + " " + resultSet.getString("last_name")
                                + "-" + resultSet.getInt(3));
        }

        //closing all connections
        resultSet.close();
        statement.close();
        connection.close();


    }
}
