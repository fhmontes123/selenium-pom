package com.selenium.pom.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
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
        // driver = new ChromeDriver();
        try {
            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444"),
                    new ChromeOptions()
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }

    // Configuracion con multiples navegadores
    public WebDriver driverConnection(String browser) {
        try {
            switch (browser.toLowerCase()) {
                case "firefox":
                    driver = new RemoteWebDriver(
                            new URL("http://localhost:4444"),
                            new FirefoxOptions()
                    );
                    break;
                case "edge":
                    driver = new RemoteWebDriver(
                            new URL("http://localhost:4444"),
                            new EdgeOptions()
                    );
                    break;
                default:
                    driver = new RemoteWebDriver(
                            new URL("http://localhost:4444"),
                            new ChromeOptions()
                    );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return driver;
    }

    // Configuracion para LamdaTest (NUBE)
    public WebDriver driverConnectionCloud() {
        String username = "yyy";
        String password = "xxx";

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPlatformName("Windows 10");
        chromeOptions.setBrowserVersion("latest");

        HashMap<String, Object> chromeOptionMap = new HashMap<>();
        chromeOptionMap.put("build", "Mi Primer Build en la Nube");
        chromeOptionMap.put("name", "Prueba de login con DDT");
        chromeOptionMap.put("w3c", true);
        chromeOptions.setCapability("LT:Options", chromeOptionMap);

        String hubURL = "https://" + username + ":" + password + "@hub.lambdatest.com/wd/hub";
        try {
            driver = new RemoteWebDriver(new URL(hubURL), chromeOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
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
