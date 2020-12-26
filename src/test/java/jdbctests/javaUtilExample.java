package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class javaUtilExample {

    String dbUrl ="jdbc:oracle:thin:@3.81.99.109:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";


    @Test
    public void metadata() throws SQLException {
        //create the connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from countries");

        //get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("User= "+dbMetadata.getUserName());
        System.out.println("Database Product Name = " + dbMetadata.getDatabaseProductName());
        System.out.println("Database Product version = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver =" + dbMetadata.getDriverName());
        System.out.println("Driver =" + dbMetadata.getDriverVersion());

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //how many columns we have ?
        System.out.println("Column count = " + rsMetadata.getColumnCount());


        //list for keeping all rows as a map
        List<Map<String,Object>> queryData = new ArrayList<>();

        //-----------------------
        Map<String,Object> row1 = new HashMap<>();

        row1.put("first_name","Steven");
        row1.put("last_name","King");
        row1.put("salary",24000);
        row1.put("job_id","AD_PRESS");

        System.out.println(row1.toString());

        System.out.println(row1.get("first_name"));

        Map<String,Object> row2 = new HashMap<>();

        row2.put("first_name","Neena");
        row2.put("last_name","Kochhar");
        row2.put("salary",17000);
        row2.put("job_id","AD_VP");

        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);

        System.out.println(queryData.get(0).get("salary"));


        //closing all connections
        resultSet.close();
        statement.close();
        connection.close();

    }
}
