package com.automation.homework4Tests;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

public class Question3 {
    private By yearBy = By.xpath("//select[@id='year']");
    private By monthBy = By.xpath("//select[@id='month']");
    private By dayBy = By.xpath("//select[@id='day']");

    @Test
    public void daysOfMonthVerify() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/dropdown");
        driver.manage().window().maximize();

        Random rndm = new Random();
        int x = rndm.nextInt(99)+1921;
        System.out.println(x);

        Select yearDropDown = new Select(driver.findElement(yearBy));
        yearDropDown.selectByVisibleText(String.valueOf(x));
        BrowserUtils.wait(3);
        Select monthDropDown = new Select(driver.findElement(monthBy));
        Select dayDropDown = new Select(driver.findElement(dayBy));
        for (int i = 0 ; i < 12 ; i++){
            monthDropDown.selectByIndex(i);
            BrowserUtils.wait(2);
            String month = monthDropDown.getFirstSelectedOption().getText();
            int daysInMonth = dayDropDown.getOptions().size();
            switch(month){
                case "January":
                case "March":
                case "May":
                case "July":
                case "August":
                case "October":
                case "December":
                    Assert.assertEquals(31,daysInMonth);
                    break;
                case "April":
                case "June":
                case "September":
                case "November":
                    Assert.assertEquals(30,daysInMonth);
                    break;
                case "February":
                    if(x%4==0) Assert.assertEquals(29,daysInMonth);
                    else Assert.assertEquals(28,daysInMonth);
                    break;
            }
        }
        driver.quit();
    }
}
