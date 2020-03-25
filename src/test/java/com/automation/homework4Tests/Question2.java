package com.automation.homework4Tests;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Question2 {
    private By yearBy = By.xpath("//select[@id='year']");
    private By monthBy = By.xpath("//select[@id='month']");
    private By dayBy = By.xpath("//select[@id='day']");

    @Test
    public void dateOfBirthVerify(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/dropdown");
        driver.manage().window().maximize();

        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy MMMM dd"));
        BrowserUtils.wait(2);
        System.out.println(currentDate);

        Select yearDropDown = new Select(driver.findElement(yearBy));
        Select monthDropDown = new Select(driver.findElement(monthBy));
        Select dayDropDown = new Select(driver.findElement(dayBy));
        String uiDate = yearDropDown.getFirstSelectedOption().getText()
                +" "+monthDropDown.getFirstSelectedOption().getText()
                +" "+dayDropDown.getFirstSelectedOption().getText();
        System.out.println(uiDate);

        Assert.assertTrue(uiDate.equals(currentDate));

        BrowserUtils.wait(2);
        driver.quit();
    }
}
