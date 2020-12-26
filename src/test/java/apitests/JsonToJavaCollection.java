package apitests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;
import io.restassured.RestAssured;
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
import java.util.Map;

public class JsonToJavaCollection {

    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI = ConfigurationReader.getProperty("spartanapi_url");
    }


    @Test
    public void SpartanToMap(){


        Response response = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .and().pathParam("id", 14)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        Map<String,Object> spartanMap = response.body().as(Map.class);


        System.out.println(spartanMap);
        String name = (String) spartanMap.get("name");

        System.out.println("name = " + name);
    }

    @Test
    public void allSpartanWithList(){

        Response response = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .when().get("/api/spartans");

        List<Map<String,Object>> allSpartanList = response.body().as(List.class);

        System.out.println("allSpartanList = " + allSpartanList);
        System.out.println("allSpartanList.get(0) = " + allSpartanList.get(0));

        //print first spartan first name
        System.out.println(allSpartanList.get(0).get("name"));
        //get one spartan from list and assign to map
        Map<String, Object> spartan2 = allSpartanList.get(1);
        //print the name for second spartan
        System.out.println(spartan2.get("name"));
    }

    @Test
    public void regionJsonMap(){

        Response response = when().get(ConfigurationReader.getProperty("hrapi_url") + "/regions");

        assertEquals(response.statusCode(),200);

        Map<String,Object> regionMap = response.body().as(Map.class);

        Object items = regionMap.get("items");
        System.out.println("items = " + items);

        List<Map<String,Object>> itemsList = (List<Map<String,Object>>) regionMap.get("items");

        //print europe
        System.out.println(itemsList.get(0).get("region_name"));

        List<Map<String,Object>> linksList_0 = (List<Map<String,Object>>) itemsList.get(0).get("links");
        System.out.println("linksList_0.get(\"href\") = " + linksList_0.get(0).get("href"));

    }
}