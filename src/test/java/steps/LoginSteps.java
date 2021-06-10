package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import io.cucumber.java.it.Ma;
import org.junit.Assert;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.CommonMethod;
import utils.ConfigReader;

import java.util.List;
import java.util.Map;

public class LoginSteps extends CommonMethod {


    @When("user enters valid admin username and password")
    public void user_enters_valid_admin_username_and_password() {
        LoginPage loginPage=new LoginPage();
        sendText(loginPage.usernamebox, ConfigReader.getPropertyValue("username"));
        sendText(loginPage.passwordbox, ConfigReader.getPropertyValue("password"));

    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        LoginPage loginPage=new LoginPage();
       click(loginPage.loginBtn);

    }

    @Then("admin user is successfully logged in")
    public void admin_user_is_successfully_logged_in() {
        DashBoardPage dash=new DashBoardPage();
      //  Assert.assertTrue(dash.welcomemessege.isDisplayed());
        String expected = "Welcome Admin";
        String actual = dash.welcomemessege.getText();
        Assert.assertEquals("Values do not match", expected, actual);
    }

    @When("user enters valid ess username and password")
    public void user_enters_valid_ess_username_and_password() {
       LoginPage loginPage=new LoginPage();
       sendText(loginPage.usernamebox,"johnny1234560000");
       sendText(loginPage.passwordbox,"Syntax1253!!!!");
    }

    @Then("ess user is successfully logged in")
    public void ess_user_is_successfully_logged_in() {
        DashBoardPage dash=new DashBoardPage();
        Assert.assertTrue(dash.welcomemessege.isDisplayed());
    }

    @When("user enters valid username and invalid password")
    public void user_enters_valid_username_and_invalid_password() {
        LoginPage loginPage=new LoginPage();
        sendText(loginPage.usernamebox,"Admin");
        sendText(loginPage.passwordbox,"Syntax1253!!!!");
    }

    @Then("user see invalid credentials test on login page")
    public void user_see_invalid_credentials_test_on_login_page() {
        System.out.println("Error message is displayed");
    }




    @When("user enters {string} and {string}")
    public void user_enters_and(String username, String password) {
       LoginPage loginPage=new LoginPage();
        sendText(loginPage.usernamebox,username);
        sendText(loginPage.passwordbox,password);
    }



    @When("{string} is successfully logged in")
    public void is_successfully_logged_in(String firstname) {
        System.out.println("working fine");
    }


    @When("user enters valid username and invalid password and verify the error")
    public void user_enters_valid_username_and_invalid_password_and_verify_the_error(DataTable errordata) {
        List<Map<String, String>> employeeNames = errordata.asMaps();

        for (Map<String, String> employeename : employeeNames) {

            String usernamevalue = employeename.get("username");
            String passwordvalue = employeename.get("password");
            String errorvalue = employeename.get("errormessage");
            System.out.println(usernamevalue + " " + passwordvalue + " " + errorvalue);
            LoginPage loginPage = new LoginPage();
            sendText(loginPage.usernamebox, usernamevalue);
            sendText(loginPage.passwordbox, passwordvalue);
            click(loginPage.loginBtn);
            String actual = loginPage.errormessage.getText();
            Assert.assertEquals("Values do not match", errorvalue, actual);
            System.out.println("MY test case is passed");
        }

    }


    @When("user enters different {string} and {string} and verify the {string} for all the combinations")
    public void user_enters_different_and_and_verify_the_for_all_the_combinations(String usernamevalue, String passwordvalue, String error) {
       LoginPage loginPage=new LoginPage();
       sendText(loginPage.usernamebox,usernamevalue);
       sendText(loginPage.passwordbox, passwordvalue);
       click(loginPage.loginBtn);

       String actual=loginPage.errormessage.getText();

       Assert.assertEquals("values do not match",error, actual);
    }





}
