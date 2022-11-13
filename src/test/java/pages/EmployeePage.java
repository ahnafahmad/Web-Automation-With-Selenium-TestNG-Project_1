package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class EmployeePage {

    @FindBy(className = "oxd-text")
    public List<WebElement> pim;

    @FindBy(className = "oxd-button")
    public List<WebElement> addButton;

    @FindBy(name = "firstName")
    WebElement txtFirstName;
    @FindBy(name = "lastName")
    WebElement txtLastName;

    @FindBy(className = "oxd-input")
    public List<WebElement> txtUserCred;
    @FindBy(css = "[type=submit]")
    WebElement btnSubmit;
    @FindBy(className = "oxd-switch-input")
    public WebElement toggleButton;

    @FindBy(className = "oxd-input")
    public List<WebElement> employeeId;

    @FindBy(className = "orangehrm-main-title")
    public List<WebElement> headerTitle;

    public EmployeePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public void createEmployee(String firstName, String lastName, String userName, String password, String confirmPassword, String employeeId) throws InterruptedException {
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        Thread.sleep(1000);
        txtUserCred.get(4).sendKeys(employeeId);
        txtUserCred.get(5).sendKeys(userName);
        txtUserCred.get(6).sendKeys(password);
        txtUserCred.get(7).sendKeys(confirmPassword);
        btnSubmit.click();
        Thread.sleep(2000);
    }

    public void searchEmployeeById(String employeeId) {
        txtUserCred.get(1).sendKeys(employeeId);
        btnSubmit.click();
    }
}
