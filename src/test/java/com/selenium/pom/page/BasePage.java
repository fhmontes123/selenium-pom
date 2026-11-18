package com.selenium.pom.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

// CLASE DE CONFIGURACION DEL WEBDRIVER Y FUNCIONES BASICAS REUTILIZABLES PARA TODAS LAS PAGINAS
public class BasePage {
    // Instancia de WebDriver utilizada para controlar el navegador.
    private WebDriver driver;

    // Inicializa la página base con la instancia de WebDriver recibida.
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // Crea y devuelve una instancia de ChromeDriver.
    public WebDriver chromeDriverConnection() {
        driver = new ChromeDriver();
        return driver;
    }

    // Busca y devuelve el elemento que coincide con el localizador.
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    // Busca y devuelve todos los elementos que coinciden con el localizador.
    public List<WebElement> findElementos(By locator) {
        return driver.findElements(locator);
    }

    // Obtiene el texto visible del elemento indicado.
    public String getText(WebElement element) {
        return element.getText();
    }

    // Escribe el texto recibido en el elemento identificado por el localizador.
    public void type(String inputText, By locator) {
        driver.findElement(locator).sendKeys(inputText);
    }

    // Hace clic en el elemento identificado por el localizador.
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    // Abre la URL indicada en el navegador.
    public void visit(String url) {
        driver.get(url);
    }
}
