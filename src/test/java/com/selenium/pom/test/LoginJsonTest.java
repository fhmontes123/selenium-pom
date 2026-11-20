package com.selenium.pom.test;

import com.selenium.pom.model.LoginData;
import com.selenium.pom.page.LoginPage;
import com.selenium.pom.utils.ExcelReader;
import com.selenium.pom.utils.JsonReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginJsonTest {

    private WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    public void setup() {
        loginPage = new LoginPage(driver);
        driver = loginPage.chromeDriverConnection();
        loginPage.visit("https://www.saucedemo.com/");
    }

    @DataProvider(name = "loginData")
    public Object[] getLoginData() {
        return JsonReader.readUsersSafe("files/Usuarios.json").toArray();
    }

    @Test(dataProvider = "loginData")
    public void loginUsuariosJson(LoginData user) {
        driver = loginPage.chromeDriverConnection();
        loginPage.visit("https://www.saucedemo.com/");
        loginPage.loginUser(user.getUsername(), user.getPassword());
        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html",
                "El login fallo para: " + user.getUsername());
    }

    @AfterClass
    public void tearDown() {
        if(driver != null){
            driver.quit();
        }
    }
}
