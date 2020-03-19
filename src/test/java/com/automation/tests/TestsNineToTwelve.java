package com.automation.tests;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestsNineToTwelve {
    private WebDriver driver;
    private By Code200By = By.linkText("200");
    private By Code301By = By.linkText("301");
    private By Code404By = By.linkText("404");
    private By Code500By = By.linkText("500");

    @Test
    public void Test9(){
        driver.findElement(Code200By).click();
        String expected = "This page returned a 200 status code.";
        String text = driver.findElement(By.xpath("//div[@class='example']/h3/following-sibling::p")).getText().trim();
        String actual = text.substring(0,text.indexOf(".")+1);
        BrowserUtils.wait(3);
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void Test10(){
        driver.findElement(Code301By).click();
        String expected = "This page returned a 301 status code.";
        String text = driver.findElement(By.xpath("//div[@class='example']/h3/following-sibling::p")).getText().trim();
        String actual = text.substring(0,text.indexOf(".")+1);
        BrowserUtils.wait(3);
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void Test11(){
        driver.findElement(Code404By).click();
        String expected = "This page returned a 404 status code.";
        String text = driver.findElement(By.xpath("//div[@class='example']/h3/following-sibling::p")).getText().trim();
        String actual = text.substring(0,text.indexOf(".")+1);
        BrowserUtils.wait(3);
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void Test12(){
        driver.findElement(Code500By).click();
        String expected = "This page returned a 500 status code.";
        String text = driver.findElement(By.xpath("//div[@class='example']/h3/following-sibling::p")).getText().trim();
        String actual = text.substring(0,text.indexOf(".")+1);
        BrowserUtils.wait(3);
        Assert.assertEquals(actual,expected);
    }

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com");
        driver.manage().window().maximize();
        BrowserUtils.wait(3);
        driver.findElement(By.linkText("Status Codes")).click();
        BrowserUtils.wait(3);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
