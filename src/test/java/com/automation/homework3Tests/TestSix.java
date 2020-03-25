package com.automation.homework3Tests;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestSix {
    private WebDriver driver;
    private By emailBy = By.xpath("//span[@id='email']");
    private By signUpPageBy = By.xpath("//a[@href='/sign_up']");
    private By fullNameBy = By.name("full_name");
    private By email2By = By.name("email");
    private By signUpButtonBy = By.name("wooden_spoon");
//    private By doNotReplyBy = By.xpath("//*[@id=\"schranka\"]/tr[3]/td[1]/span[2]");
//    private By doNotReplyBy = By.xpath("//*[@id=\"schranka\"]/tr[1]/td[1]/text()");
    private By doNotReplyBy = By.xpath("//tr//td[text()='Thanks for subscribing to practice.cybertekschool.com!']");

    @Test
    public void Test(){
        driver.get("https://www.tempmailaddress.com/");
        driver.manage().window().maximize();
        BrowserUtils.wait(2);
        String email = driver.findElement(emailBy).getText();
        BrowserUtils.wait(2);
        String firstName = email.substring(0,1).toUpperCase()+email.substring(1, email.indexOf("."));
        String lastName = email.substring(email.indexOf(".")+1, email.indexOf(".")+2).toUpperCase()+email.substring(email.indexOf(".")+2,email.indexOf("@"));
        driver.get("http://practice.cybertekschool.com/");
        BrowserUtils.wait(2);
        driver.findElement(signUpPageBy).click();
        BrowserUtils.wait(2);
        driver.findElement(fullNameBy).sendKeys(firstName+" "+lastName);
        BrowserUtils.wait(2);
        driver.findElement(email2By).sendKeys(email);
        BrowserUtils.wait(2);
        driver.findElement(signUpButtonBy).click();
        BrowserUtils.wait(2);
        String actual = driver.findElement(By.tagName("h3")).getText();
        BrowserUtils.wait(3);
        String expected = "Thank you for signing up. Click the button below to return to the home page.";
        BrowserUtils.wait(3);
        Assert.assertEquals(actual, expected);
        BrowserUtils.wait(3);
        for (int i = 1 ; i <= 3 ; i++){
            driver.navigate().back();
            BrowserUtils.wait(1);
        }
        WebElement doNotReplyMail = driver.findElement(doNotReplyBy);
        String actualA = doNotReplyMail.getText();
//        String expectedA = "do-not-reply@practice.cybertekschool.com";
//        driver.findElement(By.className("wpcc-btn")).click();
//        String expected2 = "do-not-reply@practice.cybertekschool.com";
//        String actual2 = driver.findElement(By.xpath("//*[@class='from']")).getText().trim();
//        Assert.assertEquals(actual2,expected2, "email was not received");
        String expectedA = "Thanks for subscribing to practice.cybertekschool.com!";
        Assert.assertTrue(actualA.equals(expectedA));

        BrowserUtils.wait(3);
        doNotReplyMail.click();
        BrowserUtils.wait(3);
        WebElement from = driver.findElement(By.id("odesilatel"));
        BrowserUtils.wait(3);
        String expectedFromMessage = "do-not-reply@practice.cybertekschool.com";
        String actualFromMessage = from.getText();
        BrowserUtils.wait(3);
        Assert.assertTrue(actualFromMessage.equals(expectedFromMessage));
        BrowserUtils.wait(3);
        WebElement subject = driver.findElement(By.id("predmet"));
        BrowserUtils.wait(3);
        String expectedSubject = "Thanks for subscribing to practice.cybertekschool.com!";
        String actualSubject = subject.getText();
        BrowserUtils.wait(3);

        Assert.assertTrue(actualSubject.equals(expectedSubject));
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
