package com.selenium.pom.test;

import com.selenium.pom.model.LoginData;
import com.selenium.pom.page.LoginPage;
import com.selenium.pom.utils.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginExcelTest {

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
        return ExcelReader.readUsers("files/Usuarios.xlsx").toArray();
    }

    @Test(dataProvider = "loginData")
    public void loginExitoso(LoginData user){
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
