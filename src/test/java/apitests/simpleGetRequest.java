package apitests;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class simpleGetRequest {

    String hrurl = "http://100.25.34.235:1000/ords/hr/regions";

    @Test
    public void test1(){
        Response response = RestAssured.get(hrurl);

        //print the status code
        System.out.println(response.statusCode());

        //print the json body
        response.prettyPrint();
//        response.prettyPeek();
    }

    /*
        Given accept type is json
        When user sends get request to regions endpoint
        Then response status code must be 200
        and body is json format
     */

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(hrurl);

        //verify response status code is 200
        Assert.assertEquals(response.statusCode(),200);

        //verify content type is application/json
        System.out.println("res = " + response.contentType());
        Assert.assertEquals(response.contentType(),"application/json");

    }

    @Test
    public void test3(){
        RestAssured.given().accept(ContentType.JSON)
                .when().get(hrurl).then()
                .assertThat().statusCode(200)
                .and().contentType("application/json");
    }
    /*
        Given accept type is json
        When user sends get request to regions/2
        Then response status code must be 200
        and body is json format
        and response body contains Americas
     */

    @Test
    public void test4(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id",2)
                .when().get(hrurl + "/{id}");

        //verify status code
        Assert.assertEquals(response.statusCode(),200);

        //verify content type
        Assert.assertEquals(response.contentType(),"application/json");

        //verify body includes Americas
        Assert.assertTrue(response.body().asString().contains("Americas"));


    }
}
