package testcases;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class GetRequestPracticeTest {

    @Test
    public void get_request_practice1(){
        //1. Register a baser URI where the request will be sent
        RestAssured.baseURI = "https://reqres.in";

        //2. Specif the request that will be sent
        RequestSpecification spec = RestAssured.given();
        Response response = spec.get("/api/users/2");

        //3. Using the response object, elt's extract each part
        //of the response (Status line, Headers, Body)
       // System.out.println(response.statusLine() );
        //System.out.println (response.statusCode() );


        //Headers
       Headers headers = response.headers();
       String entireHeaders = headers.toString();
      // System.out.println(entireHeaders);
        String contentType = headers.getValue("Content-Type");
        String server = headers.getValue("Server");
        String date = headers.getValue("Date");
        //System.out.println(contentType);
        //System.out.println(server);
        //System.out.println(date);

        //Body
        String payload = response.getBody().asString();
     // to extract a specific data from payload
        String emailVal = JsonPath.read(payload, "$.data.email");
        String firstNameVal = JsonPath.read(payload, "$.data.first_name");
        String lastNameVal = JsonPath.read(payload, "$.data.last_name");
        String adCompVal = JsonPath.read(payload, "$.ad.company");

        System.out.println("Email:" + emailVal);
        System.out.println("First Name: " + firstNameVal);
        System.out.println("Last Name: " + lastNameVal);
        System.out.println("Ad Company: " + adCompVal);







    }

}
