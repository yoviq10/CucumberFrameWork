package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.*;
public class HardCodedExamples {

//    Given: We are PREPARING the request
//    When: making the request/making the call/hitting the endpoint
//    Then: VERIFICATION/Assertions

    @Test
    public void sampleTest() {
        String baseURI = RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";

     RequestSpecification preparedRequest = given().header("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjYzMDY1NDksImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYyNjM0OTc0OSwidXNlcklkIjoiMjg0MiJ9.MUbC2ZhklbLYSxo3MXS2iOIUzw6egLw3M0Phgn3Nf00").header("Content-Type","application/json").queryParam("employee_id","20333320");

        Response response= preparedRequest.when().get("/getOneEmployee.php");

        //to print in string format
        System.out.println(response.asString());



    }

}
