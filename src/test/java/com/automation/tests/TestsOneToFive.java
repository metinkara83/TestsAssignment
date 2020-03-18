package com.automation.tests;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TestsOneToFive {
    private WebDriver driver;
    private By registrationFormBy = By.cssSelector("*[href='/registration_form']");
    private By dateOfBirthBy = By.cssSelector("input[name='birthday']");
    private By dobWarningMessageBy = By.xpath("//small[text()='The date of birth is not valid']");
    private By programmingLanguagesBy = By.xpath("//*[@class='form-check-label']");
    private By firstNameBy = By.name("firstname");
    private By firstNameWarningMessageBy = By.xpath("//small[text()='first name must be more than 2 and less than 64 characters long']");
    private By lastNameBy = By.name("lastname");
    private By lastNameWarningMessageBy = By.xpath("//small[text()='The last name must be more than 2 and less than 64 characters long']");
    private By userNameBy = By.name("username");
    private By emailBy = By.name("email");
    private By passwordBy = By.name("password");
    private By phoneBy = By.name("phone");
    private By genderBy = By.cssSelector("*[name='gender']");
    private By submitBy = By.id("wooden_spoon");
    private By successMessageBy = By.xpath("//h4[@class='alert-heading']/following-sibling::p");

    @Test (description = "Verify date of birth warning message")
    public void dateOfBirthWarning(){
        driver.findElement(dateOfBirthBy).sendKeys("wrong_dob");
        BrowserUtils.wait(3);
        Assert.assertTrue(driver.findElement(dobWarningMessageBy).isDisplayed());
    }

    @Test (description = "Verify the mentioned programming languages are displayed")
    public void programmingLanguagesDisplayed(){
        String expected1 = "C++";
        String expected2 = "Java";
        String expected3 = "JavaScript";
        List<WebElement> programmingLanguages = driver.findElements(programmingLanguagesBy);
        Assert.assertEquals(programmingLanguages.get(0).getText(),expected1);
        Assert.assertEquals(programmingLanguages.get(1).getText(),expected2);
        Assert.assertEquals(programmingLanguages.get(2).getText(),expected3);
        BrowserUtils.wait(3);
    }

    @Test (description = "Verify first name warning message")
    public void firstNameWarning(){
        driver.findElement(firstNameBy).sendKeys("a");
        BrowserUtils.wait(3);
        Assert.assertTrue(driver.findElement(firstNameWarningMessageBy).isDisplayed());
    }

    @Test (description = "Verify last name warning message")
    public void lastNameWarning(){
        driver.findElement(lastNameBy).sendKeys("a");
        BrowserUtils.wait(3);
        Assert.assertTrue(driver.findElement(lastNameWarningMessageBy).isDisplayed());
    }

    @Test (description = "Verify success message")
    public void successMessageWarning(){
        driver.findElement(firstNameBy).sendKeys("Metin");
        BrowserUtils.wait(2);
        driver.findElement(lastNameBy).sendKeys("Kara");
        BrowserUtils.wait(2);
        driver.findElement(userNameBy).sendKeys("metinkara");
        BrowserUtils.wait(2);
        driver.findElement(emailBy).sendKeys("metinkara@gmail.com");
        BrowserUtils.wait(2);
        driver.findElement(passwordBy).sendKeys("123456789");
        BrowserUtils.wait(2);
        driver.findElement(phoneBy).sendKeys("404-893-9925");
        BrowserUtils.wait(2);
        List<WebElement> genderOptions = driver.findElements(genderBy);
        genderOptions.get(0).click();
        BrowserUtils.wait(2);
        driver.findElement(dateOfBirthBy).sendKeys("08/22/1983");
        BrowserUtils.wait(2);
        Select department = new Select(driver.findElement(By.name("department")));
        department.selectByVisibleText("Department of Engineering");
        BrowserUtils.wait(2);
        Select jobTitle = new Select(driver.findElement(By.name("job_title")));
        jobTitle.selectByVisibleText("SDET");
        BrowserUtils.wait(2);
        List<WebElement> languageOptions = driver.findElements(programmingLanguagesBy);
        languageOptions.get(1).click();
        BrowserUtils.wait(4);
        driver.findElement(submitBy).click();
        BrowserUtils.wait(2);
        String expected = "You've successfully completed registration!";
        Assert.assertTrue(driver.findElement(successMessageBy).isDisplayed());
        Assert.assertEquals(driver.findElement(successMessageBy).getText(),expected);
    }

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.manage().window().maximize();
        BrowserUtils.wait(3);
        driver.findElement(registrationFormBy).click();
        BrowserUtils.wait(3);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
