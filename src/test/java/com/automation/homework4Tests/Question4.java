package com.automation.homework4Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question4 {
    private By searchDropDownBy = By.id("searchDropdownBox");

    @Test
    public void departmentSortTest(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://amazon.com");
        driver.manage().window().maximize();
        Select searchDropDown = new Select(driver.findElement(searchDropDownBy));
        String actual = searchDropDown.getFirstSelectedOption().getText();
        String expected = "All";
        Assert.assertTrue(actual.contains(expected));

        List<WebElement> departmentsSort = searchDropDown.getOptions();

        List<String> departmentsString = listConverter(departmentsSort);
        Collections.sort(departmentsString);
        System.out.println("Default sort is : "+listConverter(departmentsSort));
        System.out.println("After sorting : "+departmentsString);
        Assert.assertFalse(listConverter(departmentsSort).equals(departmentsString));

        driver.quit();
    }

    public static List<String> listConverter(List<WebElement> a){
        List<String> result = new ArrayList<>();
        for (int i = 0 ; i < a.size() ; i++){
            result.add(a.get(i).getText());
        }
        return result;
    }
}
