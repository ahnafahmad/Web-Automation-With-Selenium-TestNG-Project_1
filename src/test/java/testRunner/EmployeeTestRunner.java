package testRunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.reporters.EmailableReporter;
import pages.EmployeePage;
import pages.LoginPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class EmployeeTestRunner extends Setup {

    LoginPage loginPage;
    EmployeePage employeePage;

    @BeforeTest
    public void doLogin() {
        driver.get("https://opensource-demo.orangehrmlive.com");
        loginPage = new LoginPage(driver);

        String adminUser = "Admin";
        String adminPassword = "admin123";

        loginPage.doLogin(adminUser, adminPassword);

    }

    @Test(priority = 1)
    public void createEmployee() throws InterruptedException, IOException, ParseException {

        employeePage = new EmployeePage(driver);
        Utils utils = new Utils();

        for (int i = 0; i < 2; i++) {
            employeePage.pim.get(1).click();
            Thread.sleep(1000);
            employeePage.addButton.get(2).click();

            utils.generateRandomData();
            String firstName = utils.getFirstname();

            String lastName = utils.getLastname();

            String randomEmployeeId = String.valueOf(Utils.generateRandomNumber(0000, 9999));
            employeePage.txtUserCred.get(4).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
            String employeeId = randomEmployeeId;
            Thread.sleep(2000);

            employeePage.toggleButton.click();
            Thread.sleep(1000);

            int randomUserId = Utils.generateRandomNumber(1000, 9999);
            employeePage.txtUserCred.get(5).sendKeys(Keys.CONTROL + "a");
            employeePage.txtUserCred.get(5).sendKeys(Keys.BACK_SPACE);
            String userName = utils.getFirstname() + randomUserId;

            String password = "Password@1234";

            String confirmPassword = password;


            employeePage.createEmployee(firstName, lastName, userName, password, confirmPassword, employeeId);
            Thread.sleep(3000);


            Utils.waitForElement(driver, employeePage.headerTitle.get(0), 50);
            if (employeePage.headerTitle.get(0).isDisplayed()) {
                utils.saveJsonList(userName, password, employeeId);
            }

        }
    }

    @Test(priority = 2)
    public void searchEmployeeById() throws IOException, ParseException, InterruptedException {

        String file = "./src/test/resources/employee.json";

        List employees = Utils.readJsondata(file);
        System.out.println(employees.size());

        employeePage.pim.get(1).click();
        Thread.sleep(1000);

        for (int i = employees.indexOf(employees.size()); i <=employees.indexOf(employees.size()-1); i--) {
                employeePage = new EmployeePage(driver);
                employeePage.txtUserCred.get(1).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
                JSONObject employeeObject = (JSONObject) employees.get(employees.size()-i);
                String employeeId = (String) employeeObject.get("employeeId");

                employeePage.searchEmployeeById(employeeId);
            }
        }
}
