
import groovy.json.JsonOutput;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DemoTest {

    @Test
    public void practiceTest() {
       String url = "https://reqres.in/api/users/2";
       RestAssured
           .given()
               .get(url)
               .andReturn()
               .getBody()
               .prettyPrint();
    }

    @Test
    public void practiceTest2() {
        String url = "https://swapi.dev/api/people/1/";
        Response response = RestAssured.given()
                .get(url)
                .andReturn();
        response.getBody().prettyPrint();
    }

    @Test
    public void practiceTest4(){

        //Form a request, send it and get the response
        // Test Steps
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification reqSpec = RestAssured.given();
        Response response = reqSpec.request(Method.GET, "/api/users/2");
        String statusLine = response.getStatusLine();



    }
}



