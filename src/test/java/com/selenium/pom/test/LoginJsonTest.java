package com.selenium.pom.test;

import com.selenium.pom.model.LoginData;
import com.selenium.pom.page.LoginPage;
import com.selenium.pom.utils.ExcelReader;
import com.selenium.pom.utils.JsonReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginJsonTest {

    private WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {
        loginPage = new LoginPage(driver);
        // driver = loginPage.chromeDriverConnection();
        driver = loginPage.driverConnection(browser);
        loginPage.visit("https://www.saucedemo.com/");
    }

    @DataProvider(name = "loginData")
    public Object[] getLoginData() {
        return JsonReader.readUsersSafe("files/Usuarios.json").toArray();
    }

    @Test(dataProvider = "loginData")
    public void loginUsuariosJson(LoginData user) {
        loginPage.loginUser(user.getUsername(), user.getPassword());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html",
                "El login fallo para: " + user.getUsername());
    }

    @AfterMethod
    public void tearDown() {
        if(driver != null){
            driver.quit();
        }
    }
}
