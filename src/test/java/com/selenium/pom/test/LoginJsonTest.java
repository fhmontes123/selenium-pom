package com.selenium.pom.test;

import com.selenium.pom.model.LoginData;
import com.selenium.pom.page.LoginPage;
import com.selenium.pom.utils.ExcelReader;
import com.selenium.pom.utils.JsonReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginJsonTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setup(){
        loginPage = new LoginPage(driver);
        driver = loginPage.chromeDriverConnection();
        loginPage.visit("https://www.saucedemo.com/");
    }

    @DataProvider(name = "loginData")
    public Object[] getLoginData() {
        // return ExcelReader.readUsers("files/Usuarios.xlsx").toArray();
        return JsonReader.readUsersSafe("files/Usuarios.json").toArray();
    }

    @Test(dataProvider = "loginData")
    public void loginExitosoJson(LoginData user){
        loginPage.loginUser(user.getUsername(), user.getPassword());
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html",
                "El login a fallado para el usuario: " + user.getUsername());
    }

    @AfterMethod
    public void close(){
        if(driver != null){
            driver.quit();
        }
    }
}
