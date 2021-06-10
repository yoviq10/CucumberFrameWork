package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AddEmployeePage;
import pages.DashBoardPage;
import utils.CommonMethod;
import utils.Constants;
import utils.ExcelReading;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethod {

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        DashBoardPage dash = new DashBoardPage();
        click(dash.pimOption);
    }

    @When("user clicks on Add employee button")
    public void user_clicks_on_add_employee_button() {
        DashBoardPage dash = new DashBoardPage();
        click(dash.addEmployeeButton);
    }

    @When("user enters firstname middlename and lastname")
    public void user_enters_firstname_middlename_and_lastname() {
        AddEmployeePage add =new AddEmployeePage();
        sendText(add.firstName,"Yovi10");  //we are passing hard coated data, not good
        sendText(add.middleName, "SS");    //we need to pass through EXCEl
        sendText(add.lastName, "quis10");
    }

    @When("user enters first name {string} middle name {string} and last name {string}")
    public void user_enters_first_name_middle_name_and_last_name(String firstName, String middleName, String lastName) {
        AddEmployeePage add =new AddEmployeePage();
        sendText(add.firstName,firstName);   //this is soft coding
        sendText(add.middleName, middleName);   //info has been entered in AddEmployee.feature
        sendText(add.lastName, lastName );
    }

    //connects with AddEmployee.feature
    @When("user enters {string} {string} and {string} in the application")
    public void user_enters_and_in_the_application(String FirstName, String MiddleName, String LastName) {
        AddEmployeePage add =new AddEmployeePage();
        sendText(add.firstName,FirstName);   //pipelines in AddEmployee.feature
        sendText(add.middleName, MiddleName);   //We used the pipelines to add the names in AddEmployee.feature
        sendText(add.lastName, LastName );
    }



    @When("user clicks on save button option")
    public void user_clicks_on_save_button_option() {
       AddEmployeePage add= new AddEmployeePage();
       click(add.saveBtn);
    }

    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee added successfully");
    }

//this will go with @datatablewithheader tag in AddEmployee.feature class
    //we need to create a List in this section
    @When("add multiple employees and verify they are added successfully")
    public void add_multiple_employees_and_verify_they_are_added_successfully(DataTable employees) throws InterruptedException {
       List<Map<String, String>> employeeNames= employees.asMaps();

       for (Map<String, String>employeename:employeeNames){     //this for loop will loop through all the elements below

           String firstnamevalue = employeename.get("FirstName");
           String middlenamevalue = employeename.get("MiddleName");
           String lastnamevalue = employeename.get("LastName");

           System.out.println(firstnamevalue + " "+ middlenamevalue + " "+ lastnamevalue);


           AddEmployeePage addEmployeePage = new AddEmployeePage();
           sendText(addEmployeePage.firstName,firstnamevalue);
           sendText(addEmployeePage.middleName,middlenamevalue);
           sendText(addEmployeePage.lastName,lastnamevalue);
           click(addEmployeePage.saveBtn);

           //assertion take it as HW

           Thread.sleep(3000);

           DashBoardPage dashBoardPage=new DashBoardPage();  //becuase it is on this pg
           click(dashBoardPage.addEmployeeButton);
           Thread.sleep(2000);


       }


    }



    @When("user adds multiple employees from excel file from {string} sheet and verify they are added")
    public void user_adds_multiple_employees_from_excel_file_from_sheet_and_verify_they_are_added(String sheetname) {
        List<Map<String, String>> newemployees = ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH, sheetname);
        DashBoardPage dash = new DashBoardPage();
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        Iterator<Map<String, String>> it = newemployees.iterator();

        while (it.hasNext()){
            Map<String, String> mapNewEmp = it.next();
            sendText(addEmployeePage.firstName, mapNewEmp.get("FirstName"));
            sendText(addEmployeePage.middleName, mapNewEmp.get("MiddleName"));
            sendText(addEmployeePage.lastName, mapNewEmp.get("LastName"));
            click(addEmployeePage.saveBtn);
            //assertion complete in HW
        }
    }



}
