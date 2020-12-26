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

public class CBTrainingWithJsonPath {

    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.getProperty("ctbapi_url");
    }

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15232)
                .when().get("/students/{id}");

        assertEquals(response.statusCode(),200);

        JsonPath json = response.jsonPath();

        System.out.println(json.getString("students.firstName[0]"));

        String lastName = json.getString("students.lastName[0]");
        System.out.println("lastName = " + lastName);

        String phone = json.getString("students.contact[0].phone");
        System.out.println("phone = " + phone);

        String city = json.getString("students.company[0].address.city");
        System.out.println("city = " + city);

        int zipCode = json.getInt("students.company[0].address.zipCode");
        System.out.println("zipCode = " + zipCode);

        assertEquals(city,"McLean");
        assertEquals(zipCode,22102);
    }


    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .and().get("/student/all");

        String firstname_0 = response.path("students.firstName[0]");
        System.out.println("firstname_0 = " + firstname_0);

        Object contactId_0 = response.path("students.contact[0].contactId");
        System.out.println("contactId_0 = " + contactId_0);

        Object phone_1 = response.jsonPath().get("students.contact[1].phone");
        System.out.println("phone_1 = " + phone_1);

        Object city_1 = response.jsonPath().get("students.company[1].address.city");
        System.out.println("city_1 = " + city_1);

    }
}
