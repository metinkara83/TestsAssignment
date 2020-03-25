package com.automation.homework4Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class Question6 {

    @Test
    public void linksTest(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://w3schools.com");
        driver.manage().window().maximize();
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement link : links) {
            if(link.isDisplayed()){
                System.out.println(link.getText() +" "+link.getAttribute("href"));
            }
        }
        driver.quit();
    }
}
