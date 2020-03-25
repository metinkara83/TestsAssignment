package com.automation.homework4Tests;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class Question1 {

    @Test
    public void daysTest(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
        driver.manage().window().maximize();
        List<WebElement> daysCheckBox = driver.findElements(By.xpath("//input[@type='checkbox']"));
        List<WebElement> daysLabels = driver.findElements(By.xpath("//input[@type='checkbox']/following-sibling::label"));
        Random rndm = new Random();
        int x = rndm.nextInt(7);
        BrowserUtils.wait(2);
        if (daysCheckBox.get(x).isEnabled()){
            daysCheckBox.get(x).click();
            BrowserUtils.wait(2);
            System.out.println("The day selected randomly is = " + daysLabels.get(x).getText());
            daysCheckBox.get(x).click();
            BrowserUtils.wait(2);
        }
        for (int i = 0 ; i < 3 ; i++){
            daysCheckBox.get(4).click();
            BrowserUtils.wait(2);
            System.out.println("Friday is checked for "+(i+1)+" time/(s).");
            daysCheckBox.get(4).click();
            BrowserUtils.wait(2);
        }
        driver.quit();
    }
}
