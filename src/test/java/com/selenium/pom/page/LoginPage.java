package com.selenium.pom.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    By username = By.xpath("//*[@id=\"user-name\"]");
    By password = By.xpath("//*[@id=\"password\"]");
    By btnLogin = By.xpath("//*[@id=\"login-button\"]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Metodo que inicia sesion
    public void loginUser(String usernameText, String passwordText) {
        type(usernameText, username);
        type(passwordText, password);
        click(btnLogin);
    }
}
