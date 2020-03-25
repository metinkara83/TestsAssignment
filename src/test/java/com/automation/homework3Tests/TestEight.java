package com.automation.homework3Tests;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestEight {
    private WebDriver driver;
    private By countryBy = By.id("myCountry");

    @Test
    public void Test(){
        driver.get("http://practice.cybertekschool.com");
        driver.manage().window().maximize();
        BrowserUtils.wait(3);
        driver.findElement(By.linkText("Autocomplete")).click();
        BrowserUtils.wait(3);
        driver.findElement(countryBy).sendKeys("United States of America");
        BrowserUtils.wait(3);
        driver.findElement(By.xpath("//div/following-sibling::input")).click();
        BrowserUtils.wait(3);
        String expected = "You selected: United States of America";
        String actual = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(actual,expected);
        BrowserUtils.wait(3);
    }

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
