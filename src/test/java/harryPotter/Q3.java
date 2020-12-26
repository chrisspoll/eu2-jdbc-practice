package harryPotter;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Q3 {

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.getProperty("harryPotter_url");
    }

    @Test
    public void Q3(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/characters");

        Assert.assertEquals(response.statusCode(),409);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        Assert.assertTrue(response.statusLine().contains("Conflict"));
        System.out.println(response.body().asString());

        Assert.assertTrue(response.body().asString().contains("\"error\":\"Must pass API key for request\""));
    }
}
