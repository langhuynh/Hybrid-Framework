package account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Account_01_Register {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.manage().window().maximize();
    }
    @Test
    public void Register_01_Empty_Data(){
        driver.get("https://demo.nopcommerce.com");
        driver.findElement(By.cssSelector("a.ico-register")).click();

        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("span#FirstName-error")).getText(), "First name is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#LastName-error")).getText(), "Last name is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Email is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "Password is required.");



    }
    @Test
    public void Register_02_Invalid_Email(){
        driver.get("https://demo.nopcommerce.com");
        driver.findElement(By.cssSelector("a.ico-register")).click();

        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("Kennedy");
        driver.findElement(By.cssSelector("input#Email")).sendKeys("abc@.com@ncm");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");

        driver.findElement(By.cssSelector("button#register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Please enter a valid email address.");

    }
    @Test
    public void Register_03_Invalid_Password(){
        driver.get("https://demo.nopcommerce.com");
        driver.findElement(By.cssSelector("a.ico-register")).click();

        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("Kennedy");
        driver.findElement(By.cssSelector("input#Email")).sendKeys("abc@gmail.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");

        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("span.field-validation-error")).getText(),"<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");


    }
    @Test
    public void Register_04_Incorrect_Confirm_Password(){
        driver.get("https://demo.nopcommerce.com");
        driver.findElement(By.cssSelector("a.ico-register")).click();

        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("Kennedy");
        driver.findElement(By.cssSelector("input#Email")).sendKeys("abc@gmail.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");

        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(),"The password and confirmation password do not match.");

    }
    @Test
    public void Register_05_Success(){
        driver.get("https://demo.nopcommerce.com");
        driver.findElement(By.cssSelector("a.ico-register")).click();

        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("John");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("Kennedy");
        driver.findElement(By.cssSelector("input#Email")).sendKeys(getEmailRandom());
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");

        driver.findElement(By.cssSelector("button#register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");



    }
    @AfterClass
    public void afterClass(){

        driver.quit();
    }
    public String getEmailRandom(){
        Random random = new Random();
        return "john" + random.nextInt(9999) +"@kennedy.us";
    }

}

