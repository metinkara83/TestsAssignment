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

public class Test6Ebrar {

    private WebDriver driver;
    @BeforeMethod
    public void beforeMethod(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get("https://www.tempmailaddress.com/");
    }
    @Test
    public void test1(){
        String fakeMail=driver.findElement(By.xpath("//span[@id='email']")).getText();
        driver.navigate().to("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        BrowserUtils.wait(2);
        driver.findElement(By.cssSelector("input[name='full_name']")).sendKeys("MetinReis");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(fakeMail);
        BrowserUtils.wait(2);
        driver.findElement(By.name("wooden_spoon")).click();
        BrowserUtils.wait(2);
        String expected="Thank you for signing up. Click the button below to return to the home page.";
        String actual=driver.findElement(By.tagName("h3")).getText();
        Assert.assertEquals(expected,actual);
        driver.navigate().to("https://www.tempmailaddress.com/");
        BrowserUtils.wait(2);
        String mailFrom="do-not-reply@practice.cybertekschool.com";
        String mailExpected=driver.findElement(By.xpath("//*[@class='from']")).getText().trim();
        Assert.assertEquals(mailExpected,mailFrom);
        BrowserUtils.wait(2);
        driver.findElement(By.xpath("//tbody[@id='schranka']//tr[@data-href='2']")).click();
        Assert.assertEquals("do-not-reply@practice.cybertekschool.com",driver.findElement(By.xpath("//span[@id='odesilatel']")).getText());
        String message=driver.findElement(By.cssSelector("span[id='predmet']")).getText();
        Assert.assertTrue(message.contains("Thanks for subscribing to practice.cybertekschool.com!"));
    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}
