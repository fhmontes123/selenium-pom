package com.selenium.pom.test;

import com.selenium.pom.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    // Instancia utilizada para controlar el navegador durante las pruebas.
    private WebDriver driver;

    // Página que contiene las acciones del formulario de inicio de sesión.
    LoginPage loginPage;

    // Configura el navegador y abre la página antes de ejecutar las pruebas.
    @BeforeClass
    public void setup() {
        loginPage = new LoginPage(driver);
        driver = loginPage.chromeDriverConnection();
        loginPage.visit("https://www.saucedemo.com/");
    }

    @Test
    public void loginExitoso() {
        loginPage.loginUser("standard_user", "secret_sauce");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}