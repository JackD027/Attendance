package com.AutoAttendance.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,  Duration.ofSeconds(10)); // Set a timeout of 10 seconds
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath = "//input[@id='hrone-username']")    
    private WebElement username;
    
    @FindBy(xpath = "(//button[contains(@class, 'loginform')])[1]")
    private WebElement nextButton;
    
    @FindBy(xpath = "//input[@id='hrone-password']")    
    private WebElement password;
    
    @FindBy(xpath = "(//button[contains(@class, 'loginform')])[2]")
    private WebElement submitButton;
    
    public void Login(String user, String pass) {
        wait.until(ExpectedConditions.visibilityOf(username)).sendKeys(user);
        nextButton.click();

        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pass);
        submitButton.click();
    }
}
