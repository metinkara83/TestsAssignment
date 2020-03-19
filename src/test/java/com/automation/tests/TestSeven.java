package com.automation.tests;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestSeven {
    private WebDriver driver;
    private By confirmationBy = By.tagName("h3");

    @Test
    public void Test(){
        driver.get("http://practice.cybertekschool.com");
        driver.manage().window().maximize();
        BrowserUtils.wait(3);
        driver.findElement(By.linkText("File Upload")).click();
        BrowserUtils.wait(3);
        WebElement chooseFile = driver.findElement(By.id("file-upload"));
        BrowserUtils.wait(3);
        BrowserUtils.wait(3);
        chooseFile.sendKeys("C:\\3. CyberTek Course\\Slides\\5. Automation Test\\Class Notes 03.15.txt");
        BrowserUtils.wait(3);
        driver.findElement(By.id("file-submit")).click();
        BrowserUtils.wait(3);
        String expectedConfirmation = "File Uploaded!";
        String actualConfirmation = driver.findElement(confirmationBy).getText();
        BrowserUtils.wait(3);
        Assert.assertEquals(actualConfirmation,expectedConfirmation);
        BrowserUtils.wait(3);
        Assert.assertTrue(driver.findElement(By.id("uploaded-files")).isDisplayed());
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
