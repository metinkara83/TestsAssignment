package com.automation.homework4Tests;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Questions8to10 {
    private WebDriver driver;

    @Test
    public void cartTestQ8(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        Random rndm = new Random();
        int a = rndm.nextInt(50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='a-size-base-plus a-color-base a-text-normal']")));
        List<WebElement> searchResults = driver.findElements(By.xpath("//span[@class='a-size-base-plus a-color-base a-text-normal']"));

        List<WebElement> priceWholeResults = driver.findElements(By.xpath("//span[@class='a-price']/span[2]/span[2]"));
        List<WebElement> priceFractionResults = driver.findElements(By.xpath("//span[@class='a-price']/span[2]/span[3]"));

        String name = "";
        String price = "";

        if(!searchResults.get(a).getText().contains("wooden spoon")){
           name = searchResults.get(a).getText().trim();
           price = "$"+priceWholeResults.get(a).getText().trim()+"."+priceFractionResults.get(a).getText().trim();
           searchResults.get(a).click();
        }

        System.out.println(name);
        System.out.println(price);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='quantity']")));
        Select quantity = new Select(driver.findElement(By.xpath("//select[@id='quantity']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='productTitle']")));
        Assert.assertEquals(quantity.getFirstSelectedOption().getText().trim(),"1");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='productTitle']")).getText().trim(),name);
        Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@id,'priceblock_')]")).getText().trim(),price);
        WebElement addToCartBtn = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
        Assert.assertTrue(addToCartBtn.isDisplayed());
    }

    @Test
    public void primeTestQ9(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement mainPageFirstPrime =driver.findElement(By.xpath("(//i[@aria-label='Amazon Prime']/../../../../../..//h2/a/span)[1]"));
        String mainPageProductName = mainPageFirstPrime.getText();
        driver.findElement(By.xpath("//i[@class='a-icon a-icon-prime a-icon-medium']/../div/label/i")).click();
        String primeClickedProductName = driver.findElement(By.xpath("(//i[@aria-label='Amazon Prime']/../../../../../..//h2/a/span)[1]")).getText();
        Assert.assertEquals(primeClickedProductName, mainPageProductName);

        List<WebElement> brands = driver.findElements(By.xpath("//ul[@aria-labelledby='p_89-title']/li//i"));
        brands.get(brands.size()-1).click();
        String brandProductName = driver.findElement(By.xpath("(//i[@aria-label='Amazon Prime']/../../../../../..//h2/a/span)[1]")).getText();
        Assert.assertNotEquals(brandProductName,primeClickedProductName);
    }

    @Test
    public void moreSpoonsTestQ10(){
        List<WebElement> brands = driver.findElements(By.xpath("//ul[@aria-labelledby='p_89-title']/li/span/a/span"));
        List<String> brandsInString = new ArrayList<>();
        for (WebElement brand : brands) {
            brandsInString.add(brand.getText());
        }
        driver.findElement(By.xpath("//i[@class='a-icon a-icon-prime a-icon-medium']/../div/label/i")).click();
        List<WebElement> brands2 = driver.findElements(By.xpath("//ul[@aria-labelledby='p_89-title']/li/span/a/span"));
        List<String> brands2InString = new ArrayList<>();
        for (WebElement brand : brands2) {
            brands2InString.add(brand.getText());
        }
        Assert.assertEquals(brands2InString,brandsInString);
    }

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://amazon.com");
        driver.manage().window().maximize();

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon");
        driver.findElement(By.xpath("//div[@class='nav-search-submit nav-sprite']/input")).click();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
