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
        System.out.println( response.getStatusLine() );


    }

    @Test
    public void register_single_user_file_payload(){

        String path = System.getProperty("user.dir") + "/src/test/resources/payloads/newUser.json";
        String payload = read(path).trim();


        RestAssured.baseURI = "https://craftplacer.trexion.com";
        RequestSpecification spec = RestAssured.given();
        spec.header("Content-Type", "Application/json");
        spec.body(payload);


        Response response = spec.post("/pcl/auth/register");

        System.out.println( response.getStatusLine() );


    }


    @Test
    public void login_single_user(){
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
    public void logout_single_user() {
        //1.login a user
        String path = System.getProperty("user.dir") + "/src/test/resources/payloads/newUser.json";
        String payload = read(path).trim();

        String base_uri;
        RestAssured.baseURI = base_uri;
        RestAssured.given()
                .contentType("Application/json");
                .body(payload)
                .post("/pcl/auth/login");

                Response response = spec.post("/pcl/auth/login");

        String SessionToken = responseWeGot.getBody().asString();

//2. Logout a single logged in user
        Response response = RestAssured.given()
                .header("Authorization", sessionToken)
                .get("/pcl/auth/logout");
        System.out.println("Response status line: " + response.getStatusLine());

    }


