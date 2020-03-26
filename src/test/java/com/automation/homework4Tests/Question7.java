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
        List<String> linkStrings = new ArrayList<>();
        for (WebElement validLink : validLinks) {
            linkStrings.add(validLink.getAttribute("href"));
        }
        for (int i = 0 ; i < linkStrings.size() ; i++){
            if(!linkStrings.equals(null)){
                Assert.assertTrue(isValid(linkStrings.get(i)));
            }
        }

        driver.quit();
    }

    public static boolean isValid(String url) {
        try {
            new URL(url).toURI();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
