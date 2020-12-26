package apitests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.testng.Assert.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

public class Homework_2 {

    @BeforeClass
    public void beforeClass(){

        RestAssured.baseURI = ConfigurationReader.getProperty("spartanapi_url");
    }


    @Test
    public void q1(){
        /*Q1:
Given accept type is json
And path param id is 20
When user sends a get request to "api/spartans/{id}"
Then status code is 200
And content-type is "application/json;charset=UTF-8"
And response header contains Date
And Transfer-Encoding is chunked
And response payload values match the following:
id is 20,
name is "Lothario",
gender is "Male",
phone is 7551551687*/
        Response response = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .and().pathParam("id", 20)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        assertTrue(response.headers().hasHeaderWithName("Date"));
        assertEquals(response.header("Transfer-Encoding"),"chunked");

        JsonPath json = response.jsonPath();

        int id = json.getInt("id");
        String name = json.getString("name");
        String gender = json.getString("gender");
        long phone = json.getLong("phone");

        assertEquals(id,20);
        assertEquals(name,"Lothario");
        assertEquals(gender,"Male");
        assertEquals(phone, 7551551687L);

    }

    @Test
    public void q2(){

        /*Q2:
Given accept type is json
And query param gender = Female
And queary param nameContains = r
When user sends a get request to "api/spartans/search"
Then status code is 200
And content-type is "application/json;char"
And all genders are Female
And all names contains r
And size is 20
And totalPages is 1
And sorted is false

*/

        Response response = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "r")
                .when().get("/api/spartans/search");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        JsonPath json = response.jsonPath();

        List<String> allGender = json.getList("content.gender");

        for (String gender : allGender) {
            assertEquals(gender,"Female");
        }

        List<String> allName = json.getList("content.name");

        for (String name : allName) {
            assertTrue(name.toLowerCase().contains("r"));
        }

        int size = json.getInt("size");
        assertEquals(size,20);

        int totalPages = json.getInt("totalPages");
        assertEquals(totalPages,1);

        boolean sorted = json.getBoolean("sort.sorted");
        assertFalse(sorted);
    }
}
