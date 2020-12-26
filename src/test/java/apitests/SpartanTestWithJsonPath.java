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

public class SpartanTestWithJsonPath {

    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI = ConfigurationReader.getProperty("spartanapi_url");
    }


        /*
          Given accept type is json
          And path param spartan id is 11
          When user sends a get request to /spartans/{id}
         Then status code is 200
         And content type is Json
         And      "id": 14,
                  "name": "Grenville",
                  "gender": "Male",
                  "phone": 1065669615
    */

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .and().pathParam("id", 14)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        int id = response.path("id");
        String name = response.path("name");

        assertEquals(id,14);
        assertEquals(name,"Grenville");

        //===========================

        JsonPath json = response.jsonPath();

        int idJson = json.getInt("id");
        String nameJson = json.getString("name");
        String genderJson = json.getString("gender");
        long phoneJson = json.getLong("phone");


        assertEquals(idJson,14);
        assertEquals(nameJson,"Grenville");
        assertEquals(genderJson,"Male");
        assertEquals(phoneJson, 1065669615L);


    }
}