package com.selenium.pom.test;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ShadowDomTest {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By NOMBRE_INPUT_TEXT = By.id("txtNombre");
    private final By BTN_VALIDAR_NOMBRE = By.id("btnValidarNombre");
    private final By NOMBRE_OUTPUT_TEXT = By.id("outputNombre");

    private final By APELLIDO_INPUT_TEXT = By.id("txtApellido");
    private final By BTN_VALIDAR_APELLIDO = By.id("btnValidarApellido");
    private final By APELLIDO_OUTPUT_TEXT = By.id("outputApellido");

    private final By SHADOW_HOST = By.id("apellido-component");

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://fhmontes123.github.io/qaautomationlabs/");
    }

    @Test
    public void validarIngresarNombre(){
        WebElement nombre = driver.findElement(NOMBRE_INPUT_TEXT);
        nombre.sendKeys("Juan");
        driver.findElement(BTN_VALIDAR_NOMBRE).click();
        WebElement output = driver.findElement(NOMBRE_OUTPUT_TEXT);
        Assert.assertEquals(output.getText(), "Su nombre es Juan");
    }

    @Test
    public void validarIngresarApellido(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement host = driver.findElement(SHADOW_HOST);
        SearchContext shadowRoot = host.getShadowRoot();

        WebElement apellido = shadowRoot.findElement(APELLIDO_INPUT_TEXT);
        apellido.sendKeys("Perez");
        shadowRoot.findElement(BTN_VALIDAR_APELLIDO).click();
        WebElement output = shadowRoot.findElement(APELLIDO_OUTPUT_TEXT);
        Assert.assertEquals(output.getText(), "Su apellido es Perez");
    }

    @AfterClass
    public void close() {
        driver.quit();
    }

}
