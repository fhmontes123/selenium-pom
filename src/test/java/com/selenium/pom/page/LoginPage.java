package com.selenium.pom.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Localizadores de los elementos del formulario de inicio de sesión.
    By username = By.xpath("//input[@name='user-name']");
    By password = By.xpath("//input[@name='password']");
    By btnLogin = By.xpath("//input[@name='login-button']");

    // Inicializa la página utilizando la instancia de WebDriver recibida.
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Ingresa las credenciales y envía el formulario de inicio de sesión.
    public void loginUser(String usernameText, String passwordText) {
        type(usernameText, username);
        type(passwordText, password);
        click(btnLogin);
    }

}