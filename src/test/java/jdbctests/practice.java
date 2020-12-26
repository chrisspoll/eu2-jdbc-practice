package jdbctests;

import org.testng.annotations.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class practice {

    @Test
    public void test1(){

        DBUtils.createConnection();

        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap("select first_name, last_name,\n" +
                "substr(first_name,0,1)|| '.' ||substr(last_name,0,1)|| '.' as \"Initials\"\n" +
                "from employees");

        System.out.println(queryResultMap.toString());

        for (Map<String, Object> stringObjectMap : queryResultMap) {
            System.out.println(stringObjectMap.toString());
        }
    }

    @Test
    public void test2(){

        DBUtils.createConnection();

        List<Object> initials = DBUtils.getColumnData("select first_name, last_name,\n" +
                "substr(first_name,0,1)|| '.' ||substr(last_name,0,1)|| '.' as \"Initials\"\n" +
                "from employees", "Initials");

        for (Object initial : initials) {
            System.out.println(initial.toString());
        }
    }


}
