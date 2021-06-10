package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DashBoardPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import utils.CommonMethod;
import utils.ConfigReader;

public class EmployeeSearchSteps extends CommonMethod {

 //I commented this out since I commented out in "EmployeeSearch.feature" class to run Hooks
  /*  @Given("user navigates to hrms")
    public void user_navigates_to_hrms() {
        // Write code here that turns the phrase above into concrete actions
        setUp();

    }*/

    @Given("user is logged in with valid admin credentials") // from LoginPage class
    public void user_is_logged_in_with_valid_admin_credentials() {
        LoginPage loginPage=new LoginPage();
        sendText(loginPage.usernamebox, ConfigReader.getPropertyValue("username"));
       sendText(loginPage.passwordbox, ConfigReader.getPropertyValue("password"));
       click(loginPage.loginBtn);
    }

    @Given("user navigates to employee list page")  //from the DashBoardPg class
    public void user_navigates_to_employee_list_page() {
        DashBoardPage dash=new DashBoardPage();
        click(dash.pimOption);
        click(dash.employeeListOption);
    }

    @When("user enters valid employee id") //from employee list pg
    public void user_enters_valid_employee_id() {
        EmployeeListPage empList=new EmployeeListPage();
        sendText(empList.idEmployee, "15518");

    }

    @When("click on search button")
    public void click_on_search_button() {
        EmployeeListPage empList=new EmployeeListPage();
        click(empList.searchButton);
    }

    @When("user enters valid employee name")
    public void user_enters_valid_employee_name() {
        EmployeeListPage empList=new EmployeeListPage();
        sendText(empList.employeenamefield, "sofiia");
    }


    @Then("user see employee information in displayed")
    public void user_see_employee_information_in_displayed() {
        System.out.println("Employee name is displayed");
        tearDown(); //closing browser
    }
}
