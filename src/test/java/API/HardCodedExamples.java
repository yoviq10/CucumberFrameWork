package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

//    Given: We are PREPARING the request
//    When: making the request/making the call/hitting the endpoint
//    Then: VERIFICATION/Assertions

    //making this GLOBAL, took the info from line 21
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjYzOTE5ODgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYyNjQzNTE4OCwidXNlcklkIjoiMjg0MiJ9.Je0kVNIrKD8X0bOriNvxa_qpAwq_WRTS0z8l1eQp46c";

    static String employee_id;   //no global and will be saved when one test case is over it will continue onto the next test case

    //@Test
    public void sampleTest() {
        // String baseURI = RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";

        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json").queryParam("employee_id", "20333320");

        Response response = preparedRequest.when().get("/getOneEmployee.php");

        //to print in string format because we want to change JSON to string format
        System.out.println(response.asString());

    }

    @Test
    public void APostCreateEmployee() {

        //Using body as a String & copying & pasting body from create employee from Postman!!
        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json").body("{\n" +
                "  \"emp_firstname\": \"nami\",\n" +
                "  \"emp_lastname\": \"swan\",\n" +
                "  \"emp_middle_name\": \"navigator\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"2021-07-10\",\n" +
                "  \"emp_status\": \"Employee\",\n" +
                "  \"emp_job_title\": \"Cloud Consultant\"\n" +
                "}"); //.log().all();   //prints/provides all extra info of params,path,headers,cookies,body..etc..being sent with the request


        Response response = preparedRequest.when().post("/createEmployee.php");

        //// response.prettyPrint(); //coverts JSON to string but don't need sysout! prettyPrint() does the same as System.out.println(response.asString());

        //jsonPath() allows us to retrieve data from json object - Just like an xpath with selenium
        employee_id = response.jsonPath().getString("Employee.employee_id");  //this returns us a string and storing it

        //// System.out.println(employee_id);

        response.then().assertThat().statusCode(201);  //this will perform an assertion

        // imported the following: import static org.hamcrest.Matchers.*; to use equalTo()
        //using Hamcrest Matchers class equalTo()
        response.then().assertThat().body("Message", equalTo("Employee Created"));

        response.then().assertThat().body("Employee.emp_firstname", equalTo("nami"));  //need the object Employee, to connect

        //verifying server, which you can find on postman under Create user>>under Headers
        response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");

    }

    //writing another method

    @Test
    public void bGetCreatedEmployee() {

        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json").queryParam("employee_id", employee_id);

        Response response = preparedRequest.when().get("/getOneEmployee.php");

        response.prettyPrint();

        String empID = response.jsonPath().getString("employee.employee_id");

        //it returns a boolean so we are storing it and calling it making sure/contains ContentEquals is employee_id
        //contentEquals makes sure that every letter/number matches the same value. unlike contains
        boolean comparingEmpIDs = empID.contentEquals(employee_id);

        //asserting that the boolean is TRUE if it's false it will fail!!
        Assert.assertTrue(comparingEmpIDs);


        //TASK: retrieve the first name & assert that the first name is the same as the one you used
        //DO NOT HAMCREST MATCHERS
        String firstName = response.jsonPath().getString("employee.emp_firstname");
        Assert.assertTrue(firstName.contentEquals("nami"));

    }

    @Test
    public void cGetAllEmployees() {

        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json");
        Response response = preparedRequest.when().get("/getAllEmployees.php");

       String allEmployees = response.prettyPrint();

       //creating object of JsonPath class
        JsonPath js = new JsonPath(allEmployees);

        //Retreievin number of employees in response body
        int count = js.getInt("Employees.size()");

        System.out.println(count);

        //print out all employees IDs from the response
        for (int i = 0; i <count ; i++) {

           String employeeIDs = js.getString("Employees["+ i +"].employee_id");  //iterating through

           //// System.out.println(employeeIDs);


            //verify stored employee ID from previous call is in response body
            if(employeeIDs.contentEquals(employee_id)){

                //printing out the employee ID
                System.out.println("Employee ID"+ employee_id + " is present in response body");
                String firstname = js.getString("Employees["+ i +"].emp_firstname");

                //printing out the first name of the employee that we created
                System.out.println("Employee name is "+ firstname);
                break;
            }
        }
    }


}
