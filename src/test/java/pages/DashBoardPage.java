package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethod;

import java.util.List;

public class DashBoardPage extends CommonMethod {

    @FindBy(id = "welcome")  //verifying welcome pg
    public WebElement welcomemessege;

    @FindBy(id= "menu_pim_viewPimModule")
    public WebElement pimOption;

    @FindBy(id= "menu_pim_viewEmployeeList")
    public WebElement employeeListOption;


    @FindBy(id= "menu_pim_addEmployee")
    public WebElement addEmployeeButton;




    @FindBy(xpath= "//div[@class='menu']/ul/li")
    public List<WebElement> dashboardtabs;




    public DashBoardPage(){   // this is a non parameterized constructor >>  then ,ake sure it is public to access in LoginTest class
        PageFactory.initElements(driver, this);
    }




}
