package harryPotter;



import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import static io.restassured.RestAssured.*;

public class Q1 {

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.getProperty("harryPotter_url");
    }

    @Test
    public void Q1(){

        Response response = given().accept(ContentType.JSON)
                .when().get("sortingHat");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");

        Character character = response.body().as(Character.class);
        String house = character.getHouse();
        boolean bool = false;

        if (house.equalsIgnoreCase("Gryffindor") || house.equalsIgnoreCase("Ravenclaw'")
            || house.equalsIgnoreCase("Slytherin") || house.equalsIgnoreCase("Hufflepuff")){
            bool = true;
        }

        assertTrue(bool);

    }
}
