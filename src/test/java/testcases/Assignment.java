package testcases;

import com.jayway.jsonpath.JsonPath;
import commons.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class Assignment extends ApiConfig {

    @Test
    public void Get_Contact_List() {
        RestAssured.baseURI = baseContactList_uri;
        Response response = RestAssured.given()
                .accept("Application/json")
                .get("/contacts");
        System.out.println(response.getStatusLine());
        response.getBody().prettyPrint();

    }

    @Test
    public void Add_Contact() {

        String path = System.getProperty("user.dir") + "/src/test/resources/payloads/AddContact.json";
        String payload = read(path).trim();

        RestAssured.baseURI = baseContactList_uri;
        Response response = RestAssured.given()
                .contentType("Application/json")
                .accept("Application/json")
                .body(payload)
                .post("/contacts");

        System.out.println(response.getStatusLine());
        System.out.println(response.getStatusCode());
        response.getBody().prettyPrint();
    }


    @Test
    public void Get_contact_id() {
        String payload = getPayload("ContactListId");
        RestAssured.baseURI = baseContactList_uri;
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(payload)
                .post("/contacts");
        String payloadFromResponse = response.getBody().asString();
        String id = JsonPath.read(payloadFromResponse, "$._id");
        System.out.println(id);
        String endpoint = "/contacts/" + id;
        Response getResponseContactList = RestAssured.given()
                .get(endpoint);
        System.out.println(getResponseContactList.getStatusCode());
        System.out.println(getResponseContactList.getStatusLine());
        response.getBody().prettyPrint();

    }

    @Test
    public void Update_contact() {


        String path = System.getProperty("user.dir") + "/src/test/resources/payloads/AddContact.json";
        String payload = read(path).trim();

        RestAssured.baseURI = baseContactList_uri;
        Response response = RestAssured.given()
                .contentType("Application/json")
                .header("Accept", "Application/json")
                .body(payload)
                .put("/contact/" + newId);

        System.out.println(response.statusCode());
        System.out.println(response.statusLine());
        response.getBody().prettyPrint();

    }

    @Test
    public void delete_contact() {
        RestAssured.baseURI = baseContactList_uri;
        Response response = RestAssured.given()
                .delete("/contacts/5f46249e17073404765782ef");
        System.out.println(response.getStatusLine());
        System.out.println(response.getStatusCode());
        response.getBody().prettyPrint();
    }
}






