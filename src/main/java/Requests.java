import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Requests {
    
    //------------------------  BASE URL  ----------------------------//
    public String baseUrl = "https://petstore.swagger.io/v2";
    //------------------------  BASE URL  ----------------------------//


    //------------------------  VARIABLES  ---------------------------//
    public static String username="gorkemisler";
    public static String firstName="gorkem";
    public static String lastName="isler";
    public static String email="gorkemis@hotmail.com";
    public static String password="123456";
    public static String phone="05054460058";
    public static String userStatus="0";
    //------------------------  VARIABLES  ---------------------------//

    public RestAssured restAssured() {
        RestAssured assured = new RestAssured();
        assured.baseURI = baseUrl;
        return assured;
    }

    public Map<String, Object> headerMap() { // bir çok requestte ortak olan header değerleri maplenir
        Map<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put("accept", "application/json");
        headerMap.put("User-Agent", "TestAutomation");
        headerMap.put("Content-Type", "application/json");
        return headerMap;
    }
    @Test
    public void register() {
        System.out.println("REGISTER");
        restAssured();
        Response response = given()
                .headers(headerMap())
                .body("{\n" +
                        "  \"id\": 9988,\n" +
                        "  \"username\": \"" + username + "\",\n" +
                        "  \"firstName\": \"" + firstName + "\",\n" +
                        "  \"lastName\": \"" + lastName + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"password\": \"" + password + "\",\n" +
                        "  \"phone\": \"" + phone + "\",\n" +
                        "  \"userStatus\": " + userStatus + "\n" +
                        "}")
                .log()
                .all()
                .post("/user").prettyPeek()
                .then()
                .extract()
                .response();
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        Assert.assertTrue("Status Code dogru degil... Status Code: " + statusCode + "", statusCode == 200);

    }
    @Test
    public void login() {
        System.out.println("LOGIN");
        restAssured();
        Response response = given()
                .headers(headerMap())
                .queryParam("username", username)
                .queryParam("password ", password)
                .log()
                .all()
                .get("/user/login").prettyPeek()
                .then()
                .extract()
                .response();
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        Assert.assertTrue("Status Code dogru degil... Status Code: " + statusCode + "", statusCode == 200);

    }


}
