package testRunner;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import setup.Setup;

public class DashboardTestRunner extends Setup {

    DashboardPage dashboardPage;
    LoginPage loginPage;

    @Test(priority = 1)
    public void doLogin(){
        driver.get("https://opensource-demo.orangehrmlive.com");
        loginPage = new LoginPage(driver);

        String adminUser = "Admin";
        String adminPassword ="admin123";

        loginPage.doLogin(adminUser,adminPassword);
    }

    @Test(priority = 2)
public void dashboardTexAssertion(){

   dashboardPage = new DashboardPage(driver);

   String actualText= dashboardPage.txtDashboard.getText();
   String expectedText = "Dashboard";

    Assert.assertTrue(actualText.contains(expectedText));
}

@Test(priority = 3)
public void isImageProfileExist(){

    Assert.assertTrue(dashboardPage.imgProfile.isDisplayed());

}

@Test(priority = 4)
public void canditateToInterviewOptionIsCLickable() throws InterruptedException {

   WebElement option = dashboardPage.canditateToInterviewOption.get(3);
   option.click();
   Thread.sleep(3000);

    String actualText= dashboardPage.txtRecruitment.getText();
    String expectedText = "Recruitment";

    Assert.assertTrue(actualText.contains(expectedText));

}

}
