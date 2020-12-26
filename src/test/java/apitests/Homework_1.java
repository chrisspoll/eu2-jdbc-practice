package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class Homework_1 {
    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.getProperty("hrapi_url");
    }

    @Test
    public void q1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", "US")
                .when().get("/countries/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertEquals(response.path("country_id"),"US");
        assertEquals(response.path("country_name"),"United States of America");
        assertEquals((int)response.path("region_id"),2);

        JsonPath json = response.jsonPath();



    }

    @Test
    public void q2(){

        /*Q2:
- Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is Json
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25*/
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"department_id\":80}")
                .when().get("/employees");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        JsonPath json = response.jsonPath();

        List<String> jobIdList = json.getList("items.job_id");

        for (String s : jobIdList) {
            assertTrue(s.startsWith("SA"));
        }

        List<Integer> depIds = json.getList("items.department_id");

        for (int depId : depIds) {
            assertEquals(depId,80);
        }
    }

    @Test
    public void q3(){

        /*Q3:
- Given accept type is Json
-Query param value q= region_id 3
- When users sends request to /countries
- Then status code is 200
- And all regions_id is 3
- And count is 6
- And hasMore is false
- And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore*/

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":3}")
                .when().get("/countries");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        JsonPath json = response.jsonPath();

        List<Integer> allRegionId = json.getList("items.region_id");

        for (int regionId : allRegionId) {
            assertEquals(regionId,3);
        }

        assertEquals(json.getInt("count"),6);

        assertFalse(json.getBoolean("hasMore"));

        List<String> actualAllCountryName = json.getList("items.country_name");
        List<String> expectedAllCountryName = new ArrayList<>();
        expectedAllCountryName.add("Australia");
        expectedAllCountryName.add("China");
        expectedAllCountryName.add("India");
        expectedAllCountryName.add("Japan");
        expectedAllCountryName.add("Malaysia");
        expectedAllCountryName.add("Singapore");

        assertEquals(actualAllCountryName,expectedAllCountryName);




    }
}
