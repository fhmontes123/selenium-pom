package com.selenium.pom.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IFrameTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testing.qaautomationlabs.com/iframe.php");
    }

    @Test
    public void validarBotonFrame1() {
        driver.switchTo().frame("iframe1");
        WebElement btnFrame1 = driver.findElement(By.xpath("//button[@type='submit']"));
        btnFrame1.click();
        driver.switchTo().defaultContent();
        WebElement output = driver.findElement(By.id("message"));
        System.out.println(output.getText());
        Assert.assertEquals(output.getText(), "You have clicked on iframe 1 button");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
