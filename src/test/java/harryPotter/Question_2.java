package harryPotter;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Question_2 {

    @BeforeClass
    public void setUp(){
        baseURI = "https://www.potterapi.com/v1";
    }

    @Test
    public void test(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("key", "invalid")
                .when().get("/characters");

        assertEquals(response.statusCode(),401);
        assertEquals(response.contentType(),"application/json; charset=utf-8");
        assertTrue(response.statusLine().contains("Unauthorized"));

        System.out.println(response.print());
        Anonym errorMessage = response.body().as(Anonym.class);

        assertEquals(errorMessage.getError(),"API Key Not Found");

        System.out.println("errorMessage.getError() = " + errorMessage.getError());


    }
}
