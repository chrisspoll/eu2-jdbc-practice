package apitests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class demo {


    String hr = "100.25.34.235:1000/ords/hr";

    @Test
    public void test(){
        Response response = RestAssured.get(hr);

        System.out.println("response.statusCode() = " + response.statusCode());

        response.prettyPrint();
    }

}
