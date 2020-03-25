package com.automation.homework4Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Question7 {

    @Test
    public void validLinksTest(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://selenium.dev/documentation/en");
        driver.manage().window().maximize();
        List<WebElement> validLinks = driver.findElements(By.tagName("a"));
        Actions a = new Actions(driver);
        for (WebElement validLink : validLinks) {
            Assert.assertTrue(!validLink.getAttribute("href").equals(null));
        }
        driver.quit();
    }
}
