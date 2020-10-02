package testcases;

import commons.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;

public class PublicContactListTest extends ApiConfig {

    @Test
    public void register_single_user() {
        String payload = "{" +
                "    \"email\": \"User12311@example.com\"," +
                "    \"password\": \"SuperSecretPassword123\"" +
                "}";
        RestAssured.baseURI = "https://craftplacer.trexion.com";
        RequestSpecification spec = RestAssured.given();
        spec.header("Content-Type", "Application/json");
        spec.body(payload);
        Response response = spec.post("/pcl/auth/register");
        System.out.println(response.getStatusLine());


    }

    @Test
    public void register_single_user_file_payload() {

        String path = System.getProperty("user.dir") + "/src/test/resources/payloads/newUser.json";
        String payload = read(path).trim();


        RestAssured.baseURI = "https://craftplacer.trexion.com";
        RequestSpecification spec = RestAssured.given();
        spec.header("Content-Type", "Application/json");
        spec.body(payload);


        Response response = spec.post("/pcl/auth/register");

        System.out.println(response.getStatusLine());


    }


    @Test
    public void login_registered_user_extract_sessionToken(){
        String payload ="{" +
                "    \"email\": \"user12311@yahoo.com\"," +
                "    \"password\": \"SuperSecretPassword123\"" +
                "}";
        RestAssured.baseURI = "https://craftplacer.trexion.com";
        RequestSpecification spec = RestAssured.given();
        spec.headers("Content-Type", "application/json");
        spec.body(payload);
        Response response = spec.post("pcl/auth/login");
        System.out.println(response.getStatusLine());
        String sessionToken = response.body().asString();
        System.out.println(sessionToken);
    }


    @Test
    public void login_single_user() {
        String payload = "{" +
                "    \"email\": \"User12311@example.com\"," +
                "    \"password\": \"SuperSecretPassword123\"" +
                "}";
        RestAssured.baseURI = "https://craftplacer.trexion.com";
        RequestSpecification spec = RestAssured.given();
        spec.header("Content-Type", "Application/json");
        spec.body(payload);
        Response response = spec.post("/pcl/auth/login");
        System.out.println(response.getStatusLine());
        String sessionToken = response.getBody().asString();
        System.out.println(response.getBody().asString());


    }

    @Test
    public void get_all_contacts() {

        RestAssured.baseURI = base_uri;
        RequestSpecification spec = RestAssured.given();
        Response response = spec.accept("Application/json")
                .get("/pcl/api/contacts");
        System.out.println("Status Line> " + response.getStatusLine());
        System.out.println("Response Body> \n" + response.getBody().prettyPrint());

    }

    @Test
    public void get_particular_contact() {
        RestAssured.baseURI = base_uri;
        RequestSpecification spec = RestAssured.given();
        Response response = spec.accept("Application/json")
                .get("/pcl/api/contacts/2");
        System.out.println("Status Line> " + response.getStatusLine());
        System.out.println("Response Body> \n" + response.getBody().asString());
    }
}