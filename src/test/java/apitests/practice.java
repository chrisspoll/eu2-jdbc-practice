package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import javax.swing.*;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class practice {

    @BeforeClass
    public void beforeClass(){

        RestAssured.baseURI = ConfigurationReader.getProperty("hrapi_url");

    }

    @Test
    public void testJsonPath(){

        Response response = RestAssured.get("/countries");
 //       response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        Object o = jsonPath.get("items.country_id[1]");
        System.out.println("o = " + o);

        Object o1 = jsonPath.get("items.links[2].href[0]");
        System.out.println("o1 = " + o1);

        List<Object> allCountryId = jsonPath.get("items.country_id");
        System.out.println("allCountryId = " + allCountryId);

        List<Object> o2 = jsonPath.get("items.findAll {it.region_id==2}.country_name");
        System.out.println("o2 = " + o2);
    }

    @Test
    public void tets2(){

        Response response = RestAssured.given().queryParam("limit", "107")
                .get("/employees");

        Assert.assertEquals(response.statusCode(),200);

        JsonPath jsonPath = response.jsonPath();

        List<Object> list1 = jsonPath.get("items.findAll {it.salary>10000}.first_name");
        System.out.println("list1 = " + list1);

        List<Object> emails = jsonPath.get("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println("emails = " + emails);

    }

    @Test
    public void test3(){

         /*
        Given accept type is json
        When user sends get request to regions endpoint
        Then response status code must be 200
        and body is json format
     */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/regions");

        Assert.assertEquals(response.statusCode(),200);

        Assert.assertEquals(response.contentType(),"application/json");
    }

    @Test
    public void test4(){

        RestAssured.given().accept(ContentType.JSON)
                .when().get("/regions").then()
                .assertThat().statusCode(200)
                .and().contentType("application/json");

    }

     /*
    given accept type is Json
    And path param id is 15
    When user sends a get request to spartans/{id}
    Then status code is 200
    And content type is Json
    And json data has following
        "id": 15,
        "name": "Meta",
        "gender": "Female",
        "phone": 1938695106
     */

    @Test
    public void tesst(){

        given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin")
                .and().pathParam("id",14)
        .when().get("http://100.25.34.235:8000/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json;charset=UTF-8")
                .and().assertThat().body("id",equalTo(14),
                "name",equalTo("Grenville"),
                "gender",equalTo("Male"),
                "phone",equalTo(1065669615L));

    }

    @Test
    public void pojotest(){

        RestAssured.baseURI = ConfigurationReader.getProperty("spartanapi_url");

        Response response = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .when().get("/api/spartans");

        List<Map<String,Object>> allSpartans = response.body().as(List.class);

        Object id = allSpartans.get(1).get("id");
        System.out.println("id = " + id);
        Assert.assertEquals(id,278);

        for (Map<String, Object> allSpartan : allSpartans) {
            System.out.println(allSpartan);
        }

        System.out.println("allSpartans = " + allSpartans);
    }

    @Test
    public void pojotest2(){

        Response response = when().get(ConfigurationReader.getProperty("hrapi_url") + "/regions");

        Map<String,Object> regionsMap = response.body().as(Map.class);

        System.out.println("regionsMap = " + regionsMap);

        System.out.println("regionsMap.get(\"items\") = " + regionsMap.get("items"));

        List<Map<String,Object>> items = (List<Map<String,Object>>) regionsMap.get("items");

        System.out.println("items.get(0).get(\"region_name\") = " + items.get(0).get("region_name"));
        System.out.println("items.get(0).get(\"links\") = " + items.get(0).get("links"));




    }

}
