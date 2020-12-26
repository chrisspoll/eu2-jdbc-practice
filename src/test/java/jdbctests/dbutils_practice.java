package jdbctests;

import org.testng.annotations.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class dbutils_practice {


    @Test
    public void test1(){


        DBUtils.createConnection();
        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap("select * from departments");
        System.out.println("queryResultMap.toString() = " + queryResultMap.toString());

        for (Map<String, Object> stringObjectMap : queryResultMap) {
            System.out.println(stringObjectMap.toString());
        }

        DBUtils.destroy();
    }

    @Test
    public void test2(){

        DBUtils.createConnection();

        Map<String, Object> rowMap = DBUtils.getRowMap("select * from employees where employee_id=100");
        System.out.println(rowMap.toString());

        DBUtils.destroy();
    }
}
