package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.DashBoardPage;
import utils.CommonMethod;

import java.util.ArrayList;
import java.util.List;

public class DashboardSteps extends CommonMethod {


    @Then("verify the following steps on dashboard")
    public void verify_the_following_steps_on_dashboard(DataTable dataTable) {

        List<String> expectedtabs = dataTable.asList();

        DashBoardPage dash = new DashBoardPage();
        List<String> actualtabs= new ArrayList<>();

        for(WebElement ele:dash.dashboardtabs){
            actualtabs.add(ele.getText());
        }

        System.out.println(expectedtabs);
        System.out.println(actualtabs);
        Assert.assertTrue(expectedtabs.equals(actualtabs));
        System.out.println("My test case is passed");

    }








}
