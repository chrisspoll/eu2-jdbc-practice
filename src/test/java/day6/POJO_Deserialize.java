package day6;


import com.google.gson.Gson;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;

public class POJO_Deserialize {

    @Test
    public void oneSpartanPojo(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 14)
                .and().auth().basic("admin", "admin")
                .when().get("http://100.25.34.235:8000/api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        //JSON to my custom class(POJO)
        //deserialization to POJO
        Spartan spartan14 = response.body().as(Spartan.class);

        System.out.println(spartan14.getId());
        System.out.println(spartan14.getName());
        System.out.println(spartan14.getGender());
        System.out.println(spartan14.getPhone());

        System.out.println(spartan14.toString());

        //assertions

        assertEquals(spartan14.getName(),"Grenville");
        assertEquals(spartan14.getGender(),"Male");
        assertEquals(spartan14.getId(),14);

    }

    @Test
    public void regionWithPojo(){

        Response response = when().get("http://100.25.34.235:1000/ords/hr/regions");

        assertEquals(response.statusCode(),200);

        //Convert JSON response to Pojo,(Custom class)

        Regions regions = response.body().as(Regions.class);

        System.out.println(regions.getCount());
        System.out.println(regions.getHasMore());

        List<Item> items = regions.getItems();
        System.out.println(items.size());
        System.out.println(items.get(3).getRegion_name());
        assertEquals(items.get(0).getRegion_name(),"Europe");
        assertEquals(items.get(1).getLinks().get(0).getHref(),"http://100.25.34.235:1000/ords/hr/regions/2");

        List<Link_> links = regions.getLinks();
        assertEquals(links.get(2).getRel(),"describedby");

    }

    @Test
    public void gson_example(){

        Gson gson = new Gson();

        //JSON to Java collections/POJO --> De-serialization

        String myJson = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";

        Spartan spartan = gson.fromJson(myJson, Spartan.class);

        Map<String,Object> map = gson.fromJson(myJson, Map.class);

        System.out.println(spartan.toString());

        //-------------------SERIALIZATION----------------
        //JAVA Collections/Pojo to JSON
        Spartan spartanEU = new Spartan(200,"Mike","Male",123123123L);

        String jsonSpartanEU = gson.toJson(spartanEU);
        System.out.println(jsonSpartanEU);

        //gson or jackson is called objectMapper,jsonparser,data binding library
    }
}
