package harryPotter;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;


public class Q2 {

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.getProperty("harryPotter_url");
    }

    @Test
    public void Q2(){

        ValidatableResponse validatableResponse = given().accept(ContentType.JSON)
                .and().queryParam("key", "invalid")
                .when().get("/characters")
                .then().assertThat().statusCode(401)
                .assertThat().contentType("application/json; charset=utf-8")
                .assertThat().statusLine("401 Unauthorized");
    }

    @Test
    public void Q2_1(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("key", "invalid")
                .when().get("/characters");

        Assert.assertEquals(response.statusCode(),401);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        Assert.assertTrue(response.statusLine().contains("Unauthorized"));

        response.prettyPrint();
        Assert.assertTrue(response.body().asString().contains("\"error\": \"API Key Not Found\""));
    }
}
