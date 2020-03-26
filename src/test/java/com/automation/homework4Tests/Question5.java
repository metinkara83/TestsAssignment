package com.automation.homework4Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class Question5 {

    @Test
    public void mainDepartmentTest(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://amazon.com/gp/site-directory");
        driver.manage().window().maximize();

        Select searchDropDown = new Select(driver.findElement(By.id("searchDropdownBox")));
        List<WebElement> departments = searchDropDown.getOptions();
        List<String> departmentsList = Question4.listConverter(departments);

        List<WebElement> departmentNames = driver.findElements(By.tagName("h2"));
        List<String> departmentNamesList = Question4.listConverter(departmentNames);

        Assert.assertFalse(departmentsList.contains(departmentNamesList));
        driver.quit();
    }
}
