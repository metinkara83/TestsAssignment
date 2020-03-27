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
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Question7 {

    @Test
    public void validLinksTest(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://selenium.dev/documentation/en");
        driver.manage().window().maximize();

        List<WebElement> validLinks = driver.findElements(By.tagName("a"));
        System.out.println(validLinks.toString());
        for (WebElement link : validLinks) {
            Assert.assertTrue(link.isEnabled());
        }
        driver.quit();
    }
}
