package apitests;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class HamcrestMatchersApiTest {



       /*
    given accept type is Json
    And path param id is 15
    When user sends a get request to spartans/{id}
    Then status code is 200
    And content type is Json
    And json data has following

    "id": 14,
    "name": "Grenville",
    "gender": "Male",
    "phone": 1065669615

     */



    @Test
    public void OneSpartanWithHamcrest(){

        given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .and().pathParam("id", 14)
                .when().get("http://100.25.34.235:8000/api/spartans/{id}")
                .then().statusCode(200).assertThat().contentType("application/json;charset=UTF-8")
                .and().body("id", equalTo(14),
                        "name", equalTo("Grenville"),
                        "gender", equalTo("Male"),
                        "phone", equalTo(1065669615));


    }


    @Test
    public void teacherData(){

        given().accept(ContentType.JSON)
                .pathParam("id",9841)
        .when().get("http://api.cybertektraining.com/teacher/{id}")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json;charset=UTF-8")
                .and().header("Vary",equalTo("Accept-Encoding"))
                .and().headers("Date",notNullValue())
                .and().body("teachers.firstName[0]",equalTo("Umit"),
                                    "teachers.lastName[0]",equalTo("kirkdokuzelli"),
                                                         "teachers.gender[0]",equalTo("Male"),
                                                         "teachers.department[0]",equalTo("English"));

    }



    @Test
    public void TeacherWithdepartments(){

        given().accept(ContentType.JSON).and()
                .pathParam("name","Computer")
                .when().get("http://api.cybertektraining.com/teacher/department/{name}")
                .then().statusCode(200).and().contentType("application/json;charset=UTF-8")
                .and().assertThat().body("teachers.firstName", hasItems("Alexander","Marteen")).log().all();

    }


}
