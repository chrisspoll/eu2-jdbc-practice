package harryPotter;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Question_3 {


    @BeforeClass
    public void setUp(){
        baseURI = "https://www.potterapi.com/v1";
    }

    @Test
    public void test(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/characters");

        assertEquals(response.statusCode(),409);
        assertEquals(response.contentType(),"application/json; charset=utf-8");
        assertTrue(response.statusLine().contains("Conflict"));

        Anonym errorMessage = response.body().as(Anonym.class);

        assertEquals(errorMessage.getError(),"Must pass API key for request");

        System.out.println("errorMessage.getError() = " + errorMessage.getError());


    }
}
