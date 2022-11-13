package testRunner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UpdateEmployeePage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class UpdateEmployeeTestRunner extends Setup {

    UpdateEmployeePage updateEmployeePage;

    @BeforeTest
    public void doLogin() throws IOException, ParseException, InterruptedException {

        driver.get("https://opensource-demo.orangehrmlive.com");
        Thread.sleep(3000);
        List data = Utils.readJsondata("./src/test/resources/employee.json");
        LoginPage loginPage = new LoginPage(driver);
        JSONObject userObj = (JSONObject) data.get(data.size() - 1);
        String userName = (String) userObj.get("userName");
        String password = (String) userObj.get("password");
        loginPage.doLogin(userName, password);

    }

    @Test
    public void updateEmployeeInfo() throws InterruptedException {

        updateEmployeePage = new UpdateEmployeePage(driver);

        updateEmployeePage.myInfo.get(2).click();
        Thread.sleep(2000);

        Utils.waitForElement(driver,updateEmployeePage.headerTitle.get(0),50);
        if(updateEmployeePage.headerTitle.get(0).isDisplayed()){
            updateEmployeePage = new UpdateEmployeePage(driver);
            updateEmployeePage.EditNationality.get(0).click();
            updateEmployeePage.EditNationality.get(0).sendKeys("b");
            updateEmployeePage.EditNationality.get(0).sendKeys(Keys.ARROW_DOWN);
            updateEmployeePage.EditNationality.get(0).sendKeys(Keys.ARROW_DOWN);
            updateEmployeePage.EditNationality.get(0).sendKeys(Keys.ENTER);
            Utils.doScroll(driver);


            List<WebElement> btnSubmit = driver.findElements(By.cssSelector("[type=submit"));
            btnSubmit.get(1).click();

        }

    }

}
