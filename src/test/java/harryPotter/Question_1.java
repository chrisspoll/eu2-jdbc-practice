package harryPotter;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question_1 {

    @BeforeClass
    public void setUp() {
        baseURI = "https://www.potterapi.com/v1";
    }

    @Test
    public void test() {

        Response response = when().get("/sortingHat");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");

        Character character = response.body().as(Character.class);
        String house = character.getHouse();

        List<String> houses = new ArrayList<>(
                Arrays.asList("Gryffindor", "Ravenclaw", "Slytherin", "Hufflepuff"));

        boolean result = false;
        for (int i = 0; i < houses.size(); i++) {
            if (house.equalsIgnoreCase(houses.get(i))){
                result = true;
                break;
            }
        }

        assertTrue(result);
    }

    @Test
    public void testt(){
        Response response = get(baseURI+"/sortingHat");

        System.out.println("response.statusCode() = " + response.statusCode());

        assertEquals(response.statusCode(),200,"verify that status code is 200");

        System.out.println(response.prettyPrint());

    }
}