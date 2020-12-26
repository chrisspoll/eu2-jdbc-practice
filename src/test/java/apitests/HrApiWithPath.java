package apitests;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class HrApiWithPath {

    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.getProperty("hrapi_url");
    }

    @Test
    public void getCountries(){

        Response response = given().queryParam("q", "{\"region_id\": 2}")
                .when().get("/countries");

        assertEquals(response.statusCode(),200);

        Object count = response.path("count");
        System.out.println("count = " + count);

        Object hasMore1 = response.jsonPath().get("hasMore");
        Object hasMore = response.path("hasMore");
        System.out.println("hasMore = " + hasMore);

        String path = response.path("items.country_name[1]");
        System.out.println("path = " + path);

        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        Object href = response.path("items.links[1].href[0]");
        System.out.println("href = " + href);

        String firstCountryId = response.path("items.country_id[0]");
        System.out.println("firstCountryId : " + firstCountryId);

        Object link2 = response.path("items.links[3].href[0]");
        System.out.println("link2 = " + link2);

        List<Integer> ids = response.path("items.region_id");
        for (int id : ids) {
            assertEquals(id,2);
        }
    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        List<String> allJobIds = response.path("items.job_id");
        for (String allJobId : allJobIds) {
            assertEquals(allJobId,"IT_PROG");
        }


    }
}
